package com.food.common.user.business.internal.dto;

import com.food.common.user.domain.User;
import lombok.Getter;

@Getter
public final class UserDto {
    private final Long id;
    private final String nickname;

    public UserDto(final User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
