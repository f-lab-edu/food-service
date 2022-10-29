package com.food.common.user.enumeration;

public enum PointType {
    COLLECT("적립"),
    USE("사용"),
    RETRIEVE("회수 (적립 취소)"),
    RECOLLECT("재적립 (사용 취소)")
    ;

    private final String description;

    PointType(String description) {
        this.description = description;
    }
}
