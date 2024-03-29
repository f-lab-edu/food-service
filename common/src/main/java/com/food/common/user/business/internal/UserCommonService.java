package com.food.common.user.business.internal;

import com.food.common.user.business.internal.dto.UserDto;
import com.food.common.user.enumeration.Role;

import javax.validation.constraints.NotNull;

public interface UserCommonService {
    UserDto findById(@NotNull Long id);
    Role findRoleById(@NotNull Long id);
}
