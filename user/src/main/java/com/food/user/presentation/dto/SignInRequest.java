package com.food.user.presentation.dto;

import com.food.common.user.enumeration.AccountType;
import lombok.Getter;

@Getter
public class SignInRequest {
    private final String userId;
    private final String password;
    private final AccountType type;

    public SignInRequest(String userId, String password, AccountType type) {
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
}
