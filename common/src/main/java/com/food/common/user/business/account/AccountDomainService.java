package com.food.common.user.business.account;

import com.food.common.user.business.account.impl.response.FoundAppAccount;
import com.food.common.user.business.account.impl.response.FoundSocialAccount;

import java.util.Optional;

public interface AccountDomainService {
    Optional<FoundAppAccount> findAppAccountByUserId(Long userId);
    Optional<FoundSocialAccount> findSocialAccountByUserId(Long userId);
}
