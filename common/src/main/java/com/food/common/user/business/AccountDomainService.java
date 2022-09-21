package com.food.common.user.business;

import com.food.common.user.business.dto.response.accountDomain.FoundAppAccount;
import com.food.common.user.business.dto.response.accountDomain.FoundSocialAccount;

import java.util.Optional;

public interface AccountDomainService {
    Optional<FoundAppAccount> findAppAccountByUserId(Long userId);
    Optional<FoundSocialAccount> findSocialUserIdByUserId(Long userId);
    Optional<FoundAppAccount> findAppAccountByLoginId(String loginId);
    Optional<FoundSocialAccount> findSocialAccountByLoginId(String loginId);

}
