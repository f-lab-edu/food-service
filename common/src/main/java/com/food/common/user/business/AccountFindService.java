package com.food.common.user.business;

import com.food.common.user.business.dto.response.accountFind.AccountFindResponse;

import javax.validation.constraints.NotNull;

public interface AccountFindService {
    AccountFindResponse findAccountByUserId(@NotNull Long userId);
}
