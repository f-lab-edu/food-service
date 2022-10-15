package com.food.common.user.business.internal.impl;

import com.food.common.user.business.internal.AccountCommonService;
import com.food.common.user.business.internal.dto.AppAccountDto;
import com.food.common.user.business.internal.dto.SocialAccountDto;
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
public class DefaultAccountCommonService implements AccountCommonService {
    private final AppAccountRepository appAccountRepository;
    private final SocialAccountRepository socialAccountRepository;
    private final UserEntityService userEntityService;

    public Optional<AppAccountDto> findAppAccountByUserId(Long userId) {
        User user = userEntityService.findById(userId);

        Optional<AppAccount> optionalAccount = appAccountRepository.findByUser(user);
        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }

        AppAccount account = optionalAccount.get();
        return Optional.of(new AppAccountDto(account));
    }

    public Optional<SocialAccountDto> findSocialUserIdByUserId(Long userId) {
        User user = userEntityService.findById(userId);

        Optional<SocialAccount> optionalAccount = socialAccountRepository.findByUser(user);
        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }

        SocialAccount account = optionalAccount.get();
        return Optional.of(new SocialAccountDto(account));
    }

    @Override
    public Optional<AppAccountDto> findAppAccountByLoginId(String loginId) {
        Optional<AppAccount> optionalAccount = appAccountRepository.findByLoginId(loginId);

        if (optionalAccount.isEmpty()) return Optional.empty();

        AppAccount account = optionalAccount.get();
        return Optional.of(new AppAccountDto(account));
    }

    @Override
    public Optional<SocialAccountDto> findSocialAccountByLoginId(String loginId) {
        Optional<SocialAccount> optionalAccount = socialAccountRepository.findByLoginId(loginId);
        if (optionalAccount.isEmpty()) return Optional.empty();

        SocialAccount account = optionalAccount.get();
        return Optional.of(new SocialAccountDto(account));
    }
}
