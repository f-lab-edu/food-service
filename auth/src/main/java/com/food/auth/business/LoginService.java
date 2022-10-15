package com.food.auth.business;

import com.food.auth.business.dto.AuthenticatedAccountInfo;
import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.auth.presentation.dto.TokenRenewResponse;
import com.food.auth.provider.AccessTokenProvider;
import com.food.common.user.business.internal.AccountCommonService;
import com.food.common.user.business.internal.LogInOutLogCommonService;
import com.food.common.user.business.internal.RefreshTokenCommonService;
import com.food.common.user.business.internal.dto.AppAccountDto;
import com.food.common.user.business.internal.dto.LogInOutLogRequest;
import com.food.common.user.business.internal.dto.RefreshTokenDto;
import com.food.common.user.business.internal.dto.SocialAccountDto;
import com.food.common.user.business.external.model.RequestUser;
import com.food.common.user.enumeration.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class LoginService {
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenCommonService refreshTokenService;
    private final AccountCommonService accountCommonService;
    private final LogInOutLogCommonService logInOutLogCommonService;

    public TokenIssueResponse login(LoginRequest request) {
        AuthenticatedAccountInfo user = request.isAppLogin() ?
                authenticateAppUser(request.getLoginId(), request.getPassword()) :
                authenticateSocialUser(request.getLoginId());

        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(user.getUserId()))
                .refreshToken(refreshTokenService.create(user.getUserId()))
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .loginId(user.getLoginId())
                .build();
    }

    private AuthenticatedAccountInfo authenticateAppUser(String loginId, String password) {
        AppAccountDto appAccountDto = accountCommonService.findAppAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        if (!appAccountDto.matchesPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        logInOutLogCommonService.saveLoginLog(LogInOutLogRequest.createLoginLog(appAccountDto.getId(), AccountType.APP));

        return new AuthenticatedAccountInfo(appAccountDto);
    }

    private AuthenticatedAccountInfo authenticateSocialUser(String loginId) {
        SocialAccountDto socialAccountDto = accountCommonService.findSocialAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        logInOutLogCommonService.saveLoginLog(LogInOutLogRequest.createLoginLog(socialAccountDto.getId(), AccountType.APP));

        return new AuthenticatedAccountInfo(socialAccountDto);
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

    public void logout(RequestUser requestUser) {
        refreshTokenService.delete(requestUser.getUserId());

        logInOutLogCommonService.saveLogoutLog(
                LogInOutLogRequest.createLogoutLog(requestUser.getAccountId(), requestUser.getAccountType()));
    }

}
