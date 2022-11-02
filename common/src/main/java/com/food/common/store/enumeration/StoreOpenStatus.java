package com.food.common.store.enumeration;

public enum StoreOpenStatus {
    OPEN("운영 중"),
    CLOSED("운영 종료")
    ;

    private final String description;

    StoreOpenStatus(String description) {
        this.description = description;
    }
}
