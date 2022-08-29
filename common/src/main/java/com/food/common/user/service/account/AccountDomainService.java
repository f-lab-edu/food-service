package com.food.common.user.service.account;

import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.SocialAccountRepository;
import com.food.common.user.service.account.response.FoundAppAccount;
import com.food.common.user.service.account.response.FoundSocialAccount;
import com.food.common.user.service.user.UserDomainService;
import com.food.common.user.service.user.response.FoundUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountDomainService {
    private final AppAccountRepository appAccountRepository;
    private final SocialAccountRepository socialAccountRepository;

    private final UserDomainService userDomainService;

    public Optional<FoundAppAccount> findAppAccountByUserId(Long userId) {
        User user = findUser(userId);

        Optional<AppAccount> foundAppAccount = appAccountRepository.findByUser(user);
        if (foundAppAccount.isEmpty()) {
            return Optional.empty();
        }

        AppAccount account = foundAppAccount.get();
        return Optional.of(new FoundAppAccount(account, getFoundUser(user)));
    }

    public Optional<FoundSocialAccount> findSocialAccountByUserId(Long userId) {
        User user = findUser(userId);

        Optional<SocialAccount> foundSocialAccount = socialAccountRepository.findByUser(user);
        if (foundSocialAccount.isEmpty()) {
            return Optional.empty();
        }

        SocialAccount account = foundSocialAccount.get();
        return Optional.of(new FoundSocialAccount(account, getFoundUser(user)));
    }

    private FoundUser getFoundUser(User user) {
        return userDomainService.getFoundUser(user);
    }

    private User findUser(Long userId) {
        return userDomainService.findEntityById(userId);
    }
}
