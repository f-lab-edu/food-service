package com.food.common.user.business.account.impl.response;

import com.food.common.user.domain.SocialAccount;
import com.food.common.user.business.user.impl.response.FoundUser;
import lombok.Getter;

@Getter
public class FoundSocialAccount {
    private String loginId;
    private FoundUser user;

    public FoundSocialAccount(SocialAccount account, FoundUser user) {
        this.loginId = account.getLoginId();
        this.user = user;
    }
}
