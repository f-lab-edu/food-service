package com.food.common.user.business.external.impl;

import com.food.common.user.business.external.RefreshTokenCommonService;
import com.food.common.user.business.external.dto.RefreshTokenDto;
import com.food.common.user.business.internal.UserEntityService;
import com.food.common.user.domain.RefreshToken;
import com.food.common.user.domain.User;
import com.food.common.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultRefreshTokenCommonService implements RefreshTokenCommonService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserEntityService userEntityService;

    @Override
    public RefreshTokenDto create(Long userId) {
        User user = userEntityService.findById(userId);
        String tokenValue = createTokenValue();
        RefreshToken savedRefreshToken = refreshTokenRepository.save(RefreshToken.create(tokenValue, user));

        return new RefreshTokenDto(savedRefreshToken);
    }

    private String createTokenValue() {
        String tokenValue = RefreshToken.makeValue();
        while (refreshTokenRepository.existsByValue(tokenValue)) {
            tokenValue = RefreshToken.makeValue();
        }

        return tokenValue;
    }

    @Override
    public RefreshTokenDto findByValue(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenValue)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리프레시 토큰입니다. refreshToken=" + refreshTokenValue));;

        return new RefreshTokenDto(refreshToken);
    }

    @Override
    public void delete(Long userId) {
        User user = userEntityService.findById(userId);
        refreshTokenRepository.deleteByUser(user);
    }
}
