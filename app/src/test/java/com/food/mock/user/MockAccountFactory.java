package com.food.mock.user;

import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.SocialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockAccountFactory {

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Autowired
    private SocialAccountRepository socialAccountRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public AppAccount appAccount(User user) {
        AppAccount appAccount = MockAppAccount.builder()
                .user(user)
                .build();

        return appAccountRepository.save(appAccount);
    }

    public AppAccount appAccount(User user, String loginId) {
        AppAccount appAccount = MockAppAccount.builder()
                .user(user)
                .loginId(loginId)
                .build();

        return appAccountRepository.save(appAccount);
    }

    public SocialAccount socialAccount(User user) {
        SocialAccount socialAccount = MockSocialAccount.builder()
                .user(user)
                .build();

        return socialAccountRepository.save(socialAccount);
    }
}
