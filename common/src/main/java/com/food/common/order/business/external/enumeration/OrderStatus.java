package com.food.common.order.business.external.enumeration;

public enum OrderStatus {
    REQUEST("주문 요청 중"),
    COOKING("조리 중"),
    COMPLETED("조리 완료"),
    CANCELED("취소")
            ;

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
