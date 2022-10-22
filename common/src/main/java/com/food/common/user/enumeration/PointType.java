package com.food.common.user.enumeration;

public enum PointType {
    COLLECT("적립"),
    USE("사용")
    ;

    private final String description;

    PointType(String description) {
        this.description = description;
    }
}
