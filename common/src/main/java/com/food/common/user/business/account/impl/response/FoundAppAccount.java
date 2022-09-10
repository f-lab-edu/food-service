package com.food.common.user.business.account.impl.response;

import com.food.common.user.domain.AppAccount;
import com.food.common.user.business.user.impl.response.FoundUser;
import lombok.Getter;

@Getter
public class FoundAppAccount {
    private String loginId;
    private String password;
    private FoundUser user;

    public FoundAppAccount(AppAccount account, FoundUser user) {
        this.loginId = account.getLoginId();
        this.password = account.getPassword();
        this.user = user;
    }
}
