package com.food.user.service;

import com.food.common.user.service.account.AccountDomainService;
import com.food.common.user.service.account.response.FoundAppAccount;
import com.food.common.user.service.account.response.FoundSocialAccount;
import com.food.user.service.dto.AccountFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserFindService {
    private final AccountDomainService accountDomainService;

    public AccountFindResponse findAccountByUserId(@NotNull Long userId) {
        Optional<FoundAppAccount> foundAppAccount = accountDomainService.findAppAccountByUserId(userId);
        if (foundAppAccount.isPresent()) {
            return new AccountFindResponse(foundAppAccount.get());
        }

        Optional<FoundSocialAccount> foundSocialAccount = accountDomainService.findSocialAccountByUserId(userId);
        if (foundSocialAccount.isPresent()) {
            return new AccountFindResponse(foundSocialAccount.get());
        }

        throw new IllegalArgumentException("계정을 찾을 수 없습니다. userId=" + userId);
    }
}
