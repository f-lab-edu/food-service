package com.food.common.user.business.external.dto;

import com.food.common.user.domain.SocialAccount;
import lombok.Getter;

@Getter
public class SocialAccountDto {
    private Long id;
    private String loginId;
    private UserDto user;

    public SocialAccountDto(SocialAccount account) {
        this.id = account.getId();
        this.loginId = account.getLoginId();
        this.user = new UserDto(account.getUser());
    }
}
