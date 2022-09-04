package com.food.user.service.dto;

import com.food.common.user.enumeration.Role;
import com.food.common.user.service.account.response.FoundAppAccount;
import com.food.common.user.service.account.response.FoundSocialAccount;
import com.food.common.user.service.user.response.FoundUser;
import lombok.Getter;

@Getter
public class AccountFindResponse {
    private Type accountType;
    private String loginId;
    private String password;
    private FoundUser user;

    public AccountFindResponse(FoundAppAccount account) {
        accountType = Type.APP;
        loginId = account.getLoginId();
        password = account.getPassword();
        user = account.getUser();
    }

    public AccountFindResponse(FoundSocialAccount account) {
        accountType = Type.SOCIAL;
        loginId = account.getLoginId();
        user = account.getUser();
    }

    public Long getUserId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

    public enum Type {
        SOCIAL, APP
    }
}
