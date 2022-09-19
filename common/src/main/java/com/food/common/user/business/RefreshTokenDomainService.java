package com.food.common.user.business;

import com.food.common.user.business.dto.response.refreshTokenDomain.IssuedRefreshToken;

public interface RefreshTokenDomainService {
    IssuedRefreshToken issue(Long userId);
}
