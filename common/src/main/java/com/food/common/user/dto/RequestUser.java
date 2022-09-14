package com.food.common.user.dto;

import com.food.common.user.business.dto.response.userDomain.FoundUser;

public interface RequestUser {
    FoundUser getUser();
    Long getUserId();
}
