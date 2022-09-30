package com.food.common.user.dto;

import com.food.common.user.enumeration.AccountType;

public interface RequestUser {
    Long getUserId();
    Long getAccountId();
    AccountType getAccountType();
}
