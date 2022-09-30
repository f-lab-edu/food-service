package com.food.auth.filter.dto;

import com.food.common.user.business.service.response.accountFind.AccountFindResponse;
import com.food.common.user.dto.RequestUser;
import com.food.common.user.enumeration.AccountType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthenticatedUser implements UserDetails, RequestUser {
    private AccountFindResponse account;

    public AuthenticatedUser(AccountFindResponse account) {
        this.account = account;
    }

    public Long getUserId() {
        return account.getUserId();
    }

    @Override
    public Long getAccountId() {
        return account.getAccountId();
    }

    @Override
    public AccountType getAccountType() {
        return account.getAccountType();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> "ROLE_" + account.getRole().name());
        return collection;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
