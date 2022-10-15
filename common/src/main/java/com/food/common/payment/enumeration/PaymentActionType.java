package com.food.common.payment.enumeration;

public enum PaymentActionType {
    PAYMENT("결제"),
    CANCELLATION("취소")
            ;

    private final String description;

    PaymentActionType(String description) {
        this.description = description;
    }
}
