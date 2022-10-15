package com.food.common.user.business.external.model;

import com.food.common.user.enumeration.AccountType;

public interface RequestUser {
    Long getUserId();
    Long getAccountId();
    AccountType getAccountType();
}
