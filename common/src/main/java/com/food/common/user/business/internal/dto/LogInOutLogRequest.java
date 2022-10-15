package com.food.common.user.business.internal.dto;

import com.food.common.user.enumeration.AccountType;
import lombok.Getter;

@Getter
public final class LogInOutLogRequest {
    private final Long accountId;
    private final AccountType accountType;

    private LogInOutLogRequest(Long accountId, AccountType accountType) {
        this.accountId = accountId;
        this.accountType = accountType;
    }

    public static LogInOutLogRequest createLoginLog(Long accountId, AccountType type) {
        return new LogInOutLogRequest(accountId, type);
    }

    public static LogInOutLogRequest createLogoutLog(Long accountId, AccountType type) {
        return new LogInOutLogRequest(accountId, type);
    }
}
