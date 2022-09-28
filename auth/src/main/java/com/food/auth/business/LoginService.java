package com.food.auth.business;

import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.auth.presentation.dto.TokenRenewResponse;
import com.food.auth.provider.AccessTokenProvider;
import com.food.common.user.business.external.RefreshTokenCommonService;
import com.food.common.user.business.external.dto.AppAccountDto;
import com.food.common.user.business.external.dto.SocialAccountDto;
import com.food.common.user.business.external.dto.RefreshTokenDto;
import com.food.common.user.business.external.dto.UserDto;
import com.food.common.user.business.external.impl.DefaultAccountCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class LoginService {
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenCommonService refreshTokenService;
    private final DefaultAccountCommonService accountDomainService;

    public TokenIssueResponse login(LoginRequest request) {
        UserDto user = request.isAppLogin() ?
                authenticateAppUser(request.getLoginId(), request.getPassword()) :
                authenticateSocialUser(request.getLoginId());

        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(user.getId()))
                .refreshToken(refreshTokenService.create(user.getId()))
                .userId(user.getId())
                .build();
    }

    private UserDto authenticateAppUser(String loginId, String password) {
        AppAccountDto appAccountDto = accountDomainService.findAppAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        if (!appAccountDto.matchesPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return appAccountDto.getUser();
    }

    private UserDto authenticateSocialUser(String loginId) {
        SocialAccountDto socialAccountDto = accountDomainService.findSocialAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        return socialAccountDto.getUser();
    }


    public TokenRenewResponse renew(String refreshTokenValue) {
        RefreshTokenDto refreshToken = refreshTokenService.findByValue(refreshTokenValue);
        if (refreshToken.hasBeenPassedExpiredDate()) {
            throw new IllegalArgumentException("유효기간이 지난 리프레시토큰입니다. 만료일시: " + refreshToken.getExpiredDate());
        }

        return TokenRenewResponse.builder()
                .accessToken(accessTokenProvider.create(refreshToken.getUserId()))
                .build();
    }

    public void logout(Long userId) {
        refreshTokenService.delete(userId);
    }

}
