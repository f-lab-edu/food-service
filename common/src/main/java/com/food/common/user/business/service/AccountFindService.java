package com.food.common.user.business.service;

import com.food.common.user.business.service.response.accountFind.AccountFindResponse;

import javax.validation.constraints.NotNull;

public interface AccountFindService {
    AccountFindResponse findAccountByUserId(@NotNull Long userId);
}
