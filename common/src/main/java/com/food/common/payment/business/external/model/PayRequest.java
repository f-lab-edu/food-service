package com.food.common.payment.business.external.model;

import com.food.common.order.business.external.common.dto.OrderDto;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
public class PayRequest {
    @NotNull
    private final Long orderId;

    @NotNull
    private final PaymentActionType actionType;

    @Size(min = 1)
    private final Set<@NotNull Element> elements = new HashSet<>();

    @Builder
    public PayRequest(Long orderId, PaymentActionType actionType, Set<Element> elements) {
        this.orderId = orderId;
        this.actionType = actionType;
        addElementsAll(elements);
    }

    private void addElementsAll(Set<Element> elements) {
        if (CollectionUtils.isEmpty(elements)) return;

        this.elements.addAll(elements);
    }

    public boolean hasDifferentTotalAmountAs(OrderDto order) {
        return getTotalAmount() == order.getAmount();
    }

    public int getTotalAmount() {
        return elements.stream()
                .map(e -> e.amount)
                .reduce(0, Integer::sum);
    }

    public boolean isUsedPoints() {
        return elements.stream()
                .anyMatch(Element::hasMethodOfPoint);
    }

    public static final class Element {
        @NotNull
        private final PaymentMethod method;

        @NotNull
        private final Integer amount;

        @Builder
        public Element(PaymentMethod method, Integer amount) {
            this.method = method;
            this.amount = amount;
        }

        private boolean hasMethodOfPoint() {
            return method.equals(PaymentMethod.POINT);
        }
    }
}
