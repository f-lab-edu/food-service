package com.food.common.user.business.impl;

import com.food.common.user.business.RefreshDomainService;
import com.food.common.user.business.dto.response.refreshTokenDomain.IssuedRefreshToken;
import com.food.common.user.domain.RefreshToken;
import com.food.common.user.domain.User;
import com.food.common.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultRefreshDomainService implements RefreshDomainService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public IssuedRefreshToken issue(User user) {
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
