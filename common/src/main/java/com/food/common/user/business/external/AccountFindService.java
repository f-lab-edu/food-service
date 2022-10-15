package com.food.common.user.business.external;

import com.food.common.user.business.external.model.AccountFindResponse;

import javax.validation.constraints.NotNull;

public interface AccountFindService {
    AccountFindResponse findAccountByUserId(@NotNull Long userId);
}
