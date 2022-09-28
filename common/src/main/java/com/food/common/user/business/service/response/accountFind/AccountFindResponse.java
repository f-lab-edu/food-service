package com.food.common.user.business.service.response.accountFind;

import com.food.common.user.business.external.dto.AppAccountDto;
import com.food.common.user.business.external.dto.SocialAccountDto;
import com.food.common.user.enumeration.AccountType;
import com.food.common.user.enumeration.Role;
import lombok.Getter;

@Getter
public class AccountFindResponse {
    private AccountType accountType;
    private String loginId;
    private String password;
    private User user;

    public AccountFindResponse(AppAccountDto account, Role roleOfUser) {
        accountType = AccountType.APP;
        loginId = account.getLoginId();
        password = account.getPassword();
        user = new User(account.getUser().getId(), roleOfUser);
    }

    public AccountFindResponse(SocialAccountDto account, Role roleOfUser) {
        accountType = AccountType.SOCIAL;
        loginId = account.getLoginId();
        user = new User(account.getUser().getId(), roleOfUser);
    }

    public Long getUserId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Getter
    private static class User {
        private Long id;
        private Role role;

        User(Long id, Role role) {
            this.id = id;
            this.role = role;
        }
    }
}
