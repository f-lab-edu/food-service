package com.food.common.user.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ALL("모든 접속자"),
    CUSTOMER("손님"),
    STORE_OWNER("사장님"),
    MANAGER("관리자")
    ;

    private final String description;
}
