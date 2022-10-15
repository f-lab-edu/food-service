package com.food.auth.presentation.dto;

import com.food.auth.provider.dto.AccessToken;
import com.food.common.user.business.internal.dto.RefreshTokenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenIssueResponse {
    private final String accessToken;
    private final String refreshToken;
    private final Long userId;
    private final String nickname;
    private final String loginId;

    @Builder
    public TokenIssueResponse(AccessToken accessToken, RefreshTokenDto refreshToken, Long userId, String nickname, String loginId) {
        this.accessToken = accessToken.getValue();
        this.refreshToken = refreshToken.getValue();
        this.userId = userId;
        this.nickname = nickname;
        this.loginId = loginId;
    }
}
