package com.food.common.payment.enumeration;

public enum PaymentMethod {
    CARD("카드"),
    ACCOUNT_TRANSFER("계좌 이체"),
    POINT("포인트")
    ;

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }
}
