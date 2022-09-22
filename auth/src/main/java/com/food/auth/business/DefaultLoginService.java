package com.food.auth.business;

import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.auth.provider.AccessTokenProvider;
import com.food.common.user.business.RefreshTokenDomainService;
import com.food.common.user.business.dto.response.accountDomain.FoundAppAccount;
import com.food.common.user.business.dto.response.accountDomain.FoundSocialAccount;
import com.food.common.user.business.dto.response.refreshTokenDomain.RefreshTokenFound;
import com.food.common.user.business.dto.response.userDomain.FoundUser;
import com.food.common.user.business.impl.DefaultAccountDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//TODO 클래스명 다시 정하기
@RequiredArgsConstructor
@Service
public class DefaultLoginService {
    private final AccessTokenProvider accessTokenProvider;
    private final RefreshTokenDomainService refreshTokenService;
    private final DefaultAccountDomainService accountDomainService;

    public TokenIssueResponse login(LoginRequest request) {
        FoundUser user = request.isAppLogin() ?
                authenticateAppUser(request.getLoginId(), request.getPassword()) :
                authenticateSocialUser(request.getLoginId());

        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(user.getId()))
                .refreshToken(refreshTokenService.create(user.getId()))
                .userId(user.getId())
                .build();
    }

    private FoundUser authenticateAppUser(String loginId, String password) {
        FoundAppAccount foundAppAccount = accountDomainService.findAppAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        if (!foundAppAccount.matchesPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return foundAppAccount.getUser();
    }

    private FoundUser authenticateSocialUser(String loginId) {
        FoundSocialAccount foundSocialAccount = accountDomainService.findSocialAccountByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다. ID=" + loginId));

        return foundSocialAccount.getUser();
    }


    public TokenIssueResponse renew(String refreshTokenValue) {
        RefreshTokenFound refreshToken = refreshTokenService.findByValue(refreshTokenValue);
        if (refreshToken.hasBeenPassedExpiredDate()) {
            throw new IllegalArgumentException("유효기간이 지난 리프레시토큰입니다. 만료일시: " + refreshToken.getExpiredDate());
        }

        return TokenIssueResponse.builder()
                .accessToken(accessTokenProvider.create(refreshToken.getOwnerId()))
                .refreshToken(refreshTokenService.create(refreshToken.getOwnerId()))
                .build();
    }

}
