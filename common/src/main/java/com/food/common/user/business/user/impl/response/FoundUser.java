package com.food.common.user.business.user.impl.response;

import com.food.common.user.domain.User;
import com.food.common.user.enumeration.Role;
import lombok.Getter;

@Getter
public class FoundUser {
    private Long id;
    private String nickname;
    private Role role;

    public FoundUser(User user, Role role) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.role = role;
    }
}
