package com.food.common.user.business;

import javax.validation.constraints.NotNull;

public interface AccountFindService {
    AccountFindResponse findAccountByUserId(@NotNull Long userId);
}
