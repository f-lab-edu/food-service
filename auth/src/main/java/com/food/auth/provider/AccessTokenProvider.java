package com.food.auth.provider;

import com.food.auth.provider.dto.AccessToken;
import com.food.auth.provider.dto.AccessTokenContent;
import com.food.auth.provider.dto.AccessTokenValidationResult;
import com.food.common.user.dto.RequestUser;

public interface AccessTokenProvider {
    AccessToken create(RequestUser requestUser);
    AccessTokenValidationResult validate(AccessToken token);
    AccessTokenContent getContent(AccessToken token);
}
