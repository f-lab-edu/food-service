package com.food.order.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.food.common.payment.business.external.model.PayRequest;
import com.food.common.payment.business.external.model.payrequest.AccountTransferPayment;
import com.food.common.payment.business.external.model.payrequest.CardPayment;
import com.food.common.payment.business.external.model.payrequest.PaymentElement;
import com.food.common.payment.business.external.model.payrequest.PointPayment;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.user.business.external.model.RequestUser;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class PayViewRequest {
    @NotNull
    private final Long orderId;

    @NotNull
    private final PaymentActionType actionType;

    @Size(min = 1)
    private final Set<@NotNull PaymentElementViewRequest> elements = new HashSet<>();

    @JsonCreator
    public PayViewRequest(@JsonProperty("orderId") Long orderId,
                          @JsonProperty("actionType") PaymentActionType actionType,
                          @JsonProperty("elements") Set<PaymentElementViewRequest> elements) {
        this.orderId = orderId;
        this.actionType = actionType;
        addElementsAll(elements);
    }

    private void addElementsAll(Set<PaymentElementViewRequest> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        this.elements.addAll(elements);
    }

    private Set<PaymentElement> toElementsOfPayRequest(Long payerId) {
        return elements.stream()
                .map(element -> element.toPaymentElements(payerId))
                .collect(Collectors.toSet());
    }

    public PayRequest toPayRequest(RequestUser requestUser) {
        return PayRequest.builder()
                .orderId(orderId)
                .actionType(actionType)
                .elements(toElementsOfPayRequest(requestUser.getUserId()))
                .payerId(requestUser.getUserId())
                .build();
    }

    public static final class PaymentElementViewRequest {
        @NotNull
        private final PaymentMethod method;

        @NotNull
        private final Integer amount;

        @JsonCreator
        public PaymentElementViewRequest(@JsonProperty("method") PaymentMethod method,
                                         @JsonProperty("amount") Integer amount) {
            this.method = method;
            this.amount = amount;
        }

        private PaymentElement toPaymentElements(Long payerId) {
            PaymentElement result = null;
            switch (method) {
                case CARD -> result = new CardPayment(amount);
                case ACCOUNT_TRANSFER -> result = new AccountTransferPayment(amount);
                case POINT -> result = new PointPayment(amount, payerId);
            }

            return result;
        }
    }
}
