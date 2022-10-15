package com.food.common.user.business.internal;

import com.food.common.user.business.internal.dto.RefreshTokenDto;

public interface RefreshTokenCommonService {
    RefreshTokenDto create(Long userId);
    RefreshTokenDto findByValue(String refreshTokenValue);
    void delete(Long userId);
}
