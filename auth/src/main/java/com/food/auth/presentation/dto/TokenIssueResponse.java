package com.food.auth.presentation.dto;

import com.food.auth.provider.dto.AccessToken;
import com.food.common.user.business.external.dto.RefreshTokenDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenIssueResponse {
    private final String accessToken;
    private final String refreshToken;
    private final Long userId;

    @Builder
    public TokenIssueResponse(AccessToken accessToken, RefreshTokenDto refreshToken, Long userId) {
        this.accessToken = accessToken.getValue();
        this.refreshToken = refreshToken.getValue();
        this.userId = userId;
    }
}
