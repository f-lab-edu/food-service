package com.food.common.payment.enumeration;

public enum PaymentStatus {
    PAYMENT_REQUEST("결제 요청"),
    PAYMENT_COMPLETED("결제 완료"),
    CANCELLATION_REQUEST("결제 취소 요청"),
    CANCELLATION_COMPLETED("결제 취소 완료")
    ;

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
