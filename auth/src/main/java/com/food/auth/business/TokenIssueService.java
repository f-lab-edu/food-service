package com.food.auth.business;

import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.auth.provider.AccessTokenProvider;
import com.food.common.user.business.RefreshTokenDomainService;
import com.food.common.user.dto.RequestUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenIssueService {
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenDomainService refreshTokenService;

    public TokenIssueResponse issue(RequestUser requestUser) {
        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(requestUser))
                .refreshToken(refreshTokenService.issue(requestUser.getUserId()))
                .userId(requestUser.getUserId())
                .build();
    }

}
