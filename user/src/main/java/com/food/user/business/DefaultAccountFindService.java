package com.food.user.business;

import com.food.common.user.business.internal.AccountCommonService;
import com.food.common.user.business.external.AccountFindService;
import com.food.common.user.business.internal.UserCommonService;
import com.food.common.user.business.internal.dto.AppAccountDto;
import com.food.common.user.business.internal.dto.SocialAccountDto;
import com.food.common.user.business.external.model.AccountFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultAccountFindService implements AccountFindService {
    private final AccountCommonService accountCommonService;
    private final UserCommonService userCommonService;

    public AccountFindResponse findAccountByUserId(@NotNull Long userId) {
        Optional<AppAccountDto> foundAppAccount = accountCommonService.findAppAccountByUserId(userId);
        if (foundAppAccount.isPresent()) {
            return new AccountFindResponse(foundAppAccount.get(), userCommonService.findRoleById(userId));
        }

        Optional<SocialAccountDto> foundSocialAccount = accountCommonService.findSocialUserIdByUserId(userId);
        if (foundSocialAccount.isPresent()) {
            return new AccountFindResponse(foundSocialAccount.get(), userCommonService.findRoleById(userId));
        }

        throw new IllegalArgumentException("계정을 찾을 수 없습니다. userId=" + userId);
    }
}
