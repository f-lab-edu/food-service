package com.food.order.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.food.common.payment.business.external.model.PayRequest;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
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

    private Set<PayRequest.Element> toElementsRequest() {
        return elements.stream()
                .map(PaymentElement::toRequest)
                .collect(Collectors.toSet());
    }

    public PayRequest toRequest() {
        return PayRequest.builder()
                .orderId(orderId)
                .actionType(actionType)
                .elements(toElementsRequest())
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

        private PayRequest.Element toRequest() {
            return PayRequest.Element.builder()
                    .method(method)
                    .amount(amount)
                    .build();
        }
    }
}
