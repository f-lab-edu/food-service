package com.food.order.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.food.common.payment.business.external.model.PayRequest;
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
    private final Set<@NotNull PaymentElement> elements = new HashSet<>();

    @JsonCreator
    public PayViewRequest(@JsonProperty("orderId") Long orderId,
                          @JsonProperty("actionType") PaymentActionType actionType,
                          @JsonProperty("elements") Set<PaymentElement> elements) {
        this.orderId = orderId;
        this.actionType = actionType;
        addElementsAll(elements);
    }

    private void addElementsAll(Set<PaymentElement> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        this.elements.addAll(elements);
    }

    private Set<PayRequest.PaymentElement> toElementsOfPayRequest(Long payerId) {
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

    public static final class PaymentElement {
        @NotNull
        private final PaymentMethod method;

        @NotNull
        private final Integer amount;

        @JsonCreator
        public PaymentElement(@JsonProperty("method") PaymentMethod method,
                              @JsonProperty("amount") Integer amount) {
            this.method = method;
            this.amount = amount;
        }

        private PayRequest.PaymentElement toPaymentElements(Long payerId) {
            PayRequest.PaymentElement result = null;
            switch (method) {
                case CARD -> result = new PayRequest.CardPayment(amount);
                case ACCOUNT_TRANSFER -> result = new PayRequest.PaymentAccountTransfer(amount);
                case POINT -> result = new PayRequest.PaymentPoint(amount, payerId);
            }

            return result;
        }
    }
}
