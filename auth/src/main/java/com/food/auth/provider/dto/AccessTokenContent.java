package com.food.auth.provider.dto;

import lombok.Getter;

@Getter
public class AccessTokenContent {
    private final Long userId;

    public AccessTokenContent(Long userId) {
        this.userId = userId;
    }
}
