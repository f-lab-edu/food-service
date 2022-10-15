package com.food.auth.business.dto;

import com.food.common.user.business.internal.dto.AppAccountDto;
import com.food.common.user.business.internal.dto.SocialAccountDto;
import lombok.Getter;

@Getter
public class AuthenticatedAccountInfo {
    private final Long userId;
    private final String nickname;
    private final String loginId;

    public AuthenticatedAccountInfo(AppAccountDto account) {
        this.userId = account.getUserId();
        this.nickname = account.getNicknameOfUser();
        this.loginId = account.getLoginId();
    }

    public AuthenticatedAccountInfo(SocialAccountDto account) {
        this.userId = account.getUserId();
        this.nickname = account.getNicknameOfUser();
        this.loginId = account.getLoginId();
    }
}
