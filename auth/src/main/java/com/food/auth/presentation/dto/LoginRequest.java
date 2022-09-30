package com.food.auth.presentation.dto;

import com.food.common.user.enumeration.AccountType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LoginRequest {
    @NotBlank
    private final String loginId;
    @NotBlank
    private final String password;
    @NotNull
    private final AccountType type;

    public LoginRequest(String loginId, String password, AccountType type) {
        this.loginId = loginId;
        this.password = password;
        this.type = type;
    }

    public boolean isAppLogin() {
        return type==AccountType.APP;
    }
}
