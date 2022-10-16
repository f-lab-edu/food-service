package com.food.common.payment.business.external.model;

import com.food.common.order.business.internal.dto.OrderDto;
import com.food.common.payment.business.external.model.payrequest.PaymentElement;
import com.food.common.payment.business.external.model.payrequest.PointPayment;
import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;
import com.food.common.payment.business.internal.model.PaymentSaveDto;
import com.food.common.payment.enumeration.PaymentActionType;
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

    @NotNull
    private final Long payerId;

    @NotNull @Size(min = 1)
    private final Set<@NotNull PaymentElement> payments = new HashSet<>();

    @Builder
    public PayRequest(Long orderId, PaymentActionType actionType, Long payerId, @NotNull Set<PaymentElement> elements) {
        this.orderId = orderId;
        this.actionType = actionType;
        this.payerId = payerId;
        this.payments.addAll(elements);
    }

    public boolean hasDifferentTotalAmountAs(OrderDto order) {
        return getTotalAmount() != order.getAmount();
    }

    public PaymentSaveDto toPaymentSaveDto() {
        return new PaymentSaveDto(orderId, actionType);
    }

    public PaymentLogsSaveDto toPaymentLogSaveDto(Long paymentId) {
        return new PaymentLogsSaveDto(paymentId, toLogsOfPaymentLogsSaveDto());
    }

    public int getTotalAmount() {
        return payments.stream()
                .map(PaymentElement::getAmount)
                .reduce(0, Integer::sum);
    }

    public Optional<PointPayment> findPointPayment() {
        Optional<PaymentElement> optionalPayment = payments.stream()
                .filter(payment -> payment instanceof PointPayment)
                .findFirst();

        if (optionalPayment.isEmpty()) return Optional.empty();

        return Optional.of((PointPayment) optionalPayment.get());
    }

    public Set<PaymentLogsSaveDto.PaymentLog> toLogsOfPaymentLogsSaveDto() {
        return payments.stream()
                .map(PaymentElement::toLogOfPaymentLogsSaveDto)
                .collect(Collectors.toSet());
    }
}
