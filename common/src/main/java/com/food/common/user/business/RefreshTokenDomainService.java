package com.food.common.user.business;

import com.food.common.user.business.dto.response.refreshTokenDomain.RefreshTokenFound;
import com.food.common.user.business.dto.response.refreshTokenDomain.RefreshTokenCreate;

public interface RefreshTokenDomainService {
    RefreshTokenCreate create(Long userId);
    RefreshTokenFound findByValue(String refreshTokenValue);
    void delete(Long userId);
}
