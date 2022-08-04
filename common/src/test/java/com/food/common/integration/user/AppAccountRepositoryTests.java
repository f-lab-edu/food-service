package com.food.common.integration.user;

import com.food.common.integration.SuperIntegrationTest;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AppAccountRepositoryTests extends SuperIntegrationTest {
    @Autowired
    private AppAccountRepository appAccountRepository;

    @DisplayName("AppAccount가 정상적으로 저장된다")
    @Test
    void shouldSaveAppAccount() {
        User user = userFactory.user();
        AppAccount account = AppAccount.create("testUser@email.com", "abcd1234!@#$", user);

        AppAccount savedAccount = appAccountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
    }
}
