package com.food.common.user.business;

import com.food.common.user.business.dto.response.refreshTokenDomain.IssuedRefreshToken;
import com.food.common.user.domain.User;

public interface RefreshDomainService {
    IssuedRefreshToken issue(User user);
}
