package com.food.common.integration.user;

import com.food.common.integration.SuperIntegrationTest;
import com.food.common.user.domain.StoreOwner;
import com.food.common.user.domain.User;
import com.food.common.user.repository.StoreOwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoreOwnerRepositoryTests extends SuperIntegrationTest {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    @DisplayName("StoreOwner가 정상적으로 저장된다")
    @Test
    void shouldSaveStoreOwner() {
        User mockUser = userFactory.user();
        StoreOwner storeOwner = StoreOwner.create(mockUser);

        StoreOwner savedStoreOwner = storeOwnerRepository.save(storeOwner);
        assertThat(savedStoreOwner).isNotNull();
        assertThat(savedStoreOwner.getId()).isNotNull();
    }

    @DisplayName("SocialAccount 유효성 실패케이이스를 검증한다")
    @Test
    void shouldBeFailedValidation_whenSaveStoreOwnerWithNullUser() {
        User mockUser = null;
        StoreOwner storeOwner = StoreOwner.create(mockUser);

        assertThatThrownBy(() -> storeOwnerRepository.save(storeOwner))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
