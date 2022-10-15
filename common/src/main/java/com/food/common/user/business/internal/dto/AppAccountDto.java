package com.food.common.user.business.internal.dto;

import com.food.common.user.domain.AppAccount;
import lombok.Getter;

@Getter
public final class AppAccountDto {
    private Long id;
    private String loginId;
    private String password;
    private UserDto user;

    public AppAccountDto(AppAccount account) {
        this.id = account.getId();
        this.loginId = account.getLoginId();
        this.password = account.getPassword();
        this.user = new UserDto(account.getUser());
    }

    public boolean matchesPassword(String password) {
        return this.password.equals(password);
    }

    public String getNicknameOfUser() {
        return user.getNickname();
    }

    public Long getUserId() {
        return user.getId();
    }
}
