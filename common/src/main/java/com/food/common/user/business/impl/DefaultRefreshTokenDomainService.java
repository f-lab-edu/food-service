package com.food.common.user.business.impl;

import com.food.common.user.business.RefreshTokenDomainService;
import com.food.common.user.business.UserDomainService;
import com.food.common.user.business.dto.response.refreshTokenDomain.IssuedRefreshToken;
import com.food.common.user.domain.RefreshToken;
import com.food.common.user.domain.User;
import com.food.common.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultRefreshTokenDomainService implements RefreshTokenDomainService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDomainService userDomainService;

    @Override
    public IssuedRefreshToken issue(Long userId) {
        User user = userDomainService.findEntityById(userId);
        String tokenValue = createTokenValue();
        RefreshToken savedRefreshToken = refreshTokenRepository.save(RefreshToken.create(tokenValue, user));

        return new IssuedRefreshToken(savedRefreshToken);
    }

    private String createTokenValue() {
        String tokenValue = RefreshToken.makeValue();
        while (refreshTokenRepository.existsByValue(tokenValue)) {
            tokenValue = RefreshToken.makeValue();
        }

        return tokenValue;
    }
}
