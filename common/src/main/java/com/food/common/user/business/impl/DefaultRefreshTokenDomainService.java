package com.food.common.user.business.impl;

import com.food.common.user.business.RefreshTokenDomainService;
import com.food.common.user.business.UserDomainService;
import com.food.common.user.business.dto.response.refreshTokenDomain.RefreshTokenFound;
import com.food.common.user.business.dto.response.refreshTokenDomain.RefreshTokenCreate;
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
    public RefreshTokenCreate create(Long userId) {
        User user = userDomainService.findEntityById(userId);
        String tokenValue = createTokenValue();
        RefreshToken savedRefreshToken = refreshTokenRepository.save(RefreshToken.create(tokenValue, user));

        return new RefreshTokenCreate(savedRefreshToken);
    }

    private String createTokenValue() {
        String tokenValue = RefreshToken.makeValue();
        while (refreshTokenRepository.existsByValue(tokenValue)) {
            tokenValue = RefreshToken.makeValue();
        }

        return tokenValue;
    }

    @Override
    public RefreshTokenFound findByValue(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenValue)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리프레시 토큰입니다. refreshToken=" + refreshTokenValue));;

        return new RefreshTokenFound(refreshToken);
    }
}
