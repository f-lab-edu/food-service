package com.food.common.user.business.impl;

import com.food.common.user.business.AccountDomainService;
import com.food.common.user.business.UserDomainService;
import com.food.common.user.business.dto.response.accountDomain.FoundAppAccount;
import com.food.common.user.business.dto.response.accountDomain.FoundSocialAccount;
import com.food.common.user.business.dto.response.userDomain.FoundUser;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.SocialAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultAccountDomainService implements AccountDomainService {
    private final AppAccountRepository appAccountRepository;
    private final SocialAccountRepository socialAccountRepository;
    private final UserDomainService userDomainService;

    public Optional<FoundAppAccount> findAppAccountByUserId(Long userId) {
        User user = findUser(userId);

        Optional<AppAccount> optionalAccount = appAccountRepository.findByUser(user);
        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }

        AppAccount account = optionalAccount.get();
        return Optional.of(new FoundAppAccount(account, getFoundUser(user)));
    }

    public Optional<FoundSocialAccount> findSocialUserIdByUserId(Long userId) {
        User user = findUser(userId);

        Optional<SocialAccount> optionalAccount = socialAccountRepository.findByUser(user);
        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }

        SocialAccount account = optionalAccount.get();
        return Optional.of(new FoundSocialAccount(account, getFoundUser(user)));
    }

    @Override
    public Optional<FoundAppAccount> findAppAccountByLoginId(String loginId) {
        Optional<AppAccount> optionalAccount = appAccountRepository.findByLoginId(loginId);

        if (optionalAccount.isEmpty()) return Optional.empty();

        AppAccount account = optionalAccount.get();
        return Optional.of(new FoundAppAccount(account, getFoundUser(account.getUser())));
    }

    @Override
    public Optional<FoundSocialAccount> findSocialAccountByLoginId(String loginId) {
        Optional<SocialAccount> optionalAccount = socialAccountRepository.findByLoginId(loginId);
        if (optionalAccount.isEmpty()) return Optional.empty();

        SocialAccount account = optionalAccount.get();
        return Optional.of(new FoundSocialAccount(account, getFoundUser(account.getUser())));
    }


    private FoundUser getFoundUser(User user) {
        return userDomainService.getFoundUser(user);
    }

    private User findUser(Long userId) {
        return userDomainService.findEntityById(userId);
    }
}
