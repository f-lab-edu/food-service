package com.food.common.user.business.external.dto;

import com.food.common.user.domain.User;
import lombok.Getter;

@Getter
public class UserDto {
    private final Long id;
    private final String nickname;

    public UserDto(final User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
