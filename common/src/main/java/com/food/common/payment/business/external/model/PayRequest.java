package com.food.common.payment.business.external.model;

import com.food.common.order.business.internal.dto.OrderDto;
import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;
import com.food.common.payment.business.internal.model.PaymentSaveDto;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.user.business.external.model.PointsUseRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PayRequest {
    @NotNull
    private final Long orderId;

    @NotNull
    private final PaymentActionType actionType;

    private final PaymentElements elements;

    @NotNull
    private final Long payerId;

    @Builder
    public PayRequest(Long orderId, PaymentActionType actionType, Set<PaymentElement> elements, Long payerId) {
        this.orderId = orderId;
        this.actionType = actionType;
        this.elements = new PaymentElements(elements);
        this.payerId = payerId;
    }

    public boolean hasDifferentTotalAmountAs(OrderDto order) {
        return getTotalAmount() == order.getAmount();
    }

    public int getTotalAmount() {
        return elements.getTotalAmount();
    }

    public Optional<PaymentPoint> findPointPayment() {
        return elements.findPointPayment();
    }

    public PaymentSaveDto toPaymentSaveDto() {
        return new PaymentSaveDto(orderId, actionType);
    }

    public PaymentLogsSaveDto toPaymentLogSaveDto(Long paymentId) {
        return new PaymentLogsSaveDto(paymentId, elements.toLogsOfPaymentLogsSaveDto());
    }

    public static abstract class PaymentElement {
        @NotNull
        protected final PaymentMethod method;

        @NotNull
        protected final Integer amount;

        protected PaymentElement(PaymentMethod method, Integer amount) {
            this.method = method;
            this.amount = amount;
        }

        Integer getAmount() {
            return amount;
        }

        protected PaymentLogsSaveDto.PaymentLog toLogOfPaymentLogsSaveDto() {
            return new PaymentLogsSaveDto.PaymentLog(method, amount);
        }
    }

    public static final class CardPayment extends PaymentElement {

        public CardPayment(Integer amount) {
            super(PaymentMethod.CARD, amount);
        }
    }

    public static final class PaymentAccountTransfer extends PaymentElement {

        public PaymentAccountTransfer(Integer amount) {
            super(PaymentMethod.ACCOUNT_TRANSFER, amount);
        }
    }

    public static final class PaymentPoint extends PaymentElement {
        private Long pointId;

        private Long payerId;

        public PaymentPoint(Integer amount, Long payerId) {
            super(PaymentMethod.POINT, amount);
            this.payerId = payerId;
        }

        public void updateUsedPointId(@NotNull Long pointId) {
            this.pointId = pointId;
        }

        public PointsUseRequest toPointsUseRequest() {
            return PointsUseRequest.builder()
                    .ownerId(payerId)
                    .amount(amount)
                    .build();
        }

        protected PaymentLogsSaveDto.PaymentLog toLogOfPaymentLogsSaveDto() {
            return new PaymentLogsSaveDto.PaymentLog(method, amount, pointId);
        }
    }

    @Getter
    public static final class PaymentElements {

        @Size(min = 1)
        private final Set<@NotNull PaymentElement> payments = new HashSet<>();


        public PaymentElements(@NotNull Set<PaymentElement> payments) {
            this.payments.addAll(payments);
        }

        public int getTotalAmount() {
            return payments.stream()
                    .map(PaymentElement::getAmount)
                    .reduce(0, Integer::sum);
        }

        public Optional<PaymentPoint> findPointPayment() {
            Optional<PaymentElement> optionalPayment = payments.stream()
                    .filter(payment -> payment instanceof PaymentPoint)
                    .findFirst();

            if (optionalPayment.isEmpty()) return Optional.empty();

            return Optional.of((PaymentPoint) optionalPayment.get());
        }

        public Set<PaymentLogsSaveDto.PaymentLog> toLogsOfPaymentLogsSaveDto() {
            return payments.stream()
                    .map(PaymentElement::toLogOfPaymentLogsSaveDto)
                    .collect(Collectors.toSet());
        }
    }
}
