package com.food.common.user.business.external;

import com.food.common.user.business.external.dto.RefreshTokenDto;

public interface RefreshTokenCommonService {
    RefreshTokenDto create(Long userId);
    RefreshTokenDto findByValue(String refreshTokenValue);
    void delete(Long userId);
}
