package com.food.common.integration.user;

import com.food.common.integration.SuperIntegrationTest;
import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.SocialAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SocialAccountRepositoryTests extends SuperIntegrationTest {

    @Autowired
    private SocialAccountRepository socialAccountRepository;

    private User mockUser;

    @BeforeEach
    void setup() {
        mockUser = userFactory.user();
    }

    @DisplayName("SocialAccount가 정상적으로 저장된다")
    @Test
    void shouldSaveAppAccount() {
        SocialAccount account = SocialAccount.create("testUser@email.com", mockUser);

        SocialAccount savedAccount = socialAccountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
    }

    @DisplayName("SocialAccount 유효성 실패케이이스를 검증한다")
    @Test
    void shouldBeFailedValidation() {
        assertAll(
                () -> assertAll(Stream.of(null, "", " ", "   ")
                        .map(wrongLoginId -> (Executable) () -> {
                            SocialAccount account = SocialAccount.create(wrongLoginId, mockUser);

                            assertThatThrownBy(() -> socialAccountRepository.save(account),
                                    "SocialAccount의 LoginId가 공백일 경우 유효성 검증에 실패해야한다. value="+wrongLoginId)
                                    .isInstanceOf(ConstraintViolationException.class);
                        }).collect(Collectors.toList())),

                () -> {
                    SocialAccount account = SocialAccount.create("testUser@email.com", null);

                    assertThatThrownBy(() -> socialAccountRepository.save(account),
                            "SocialAccount의 User가 null일 경우 유효성 검증에 실패해야한다")
                            .isInstanceOf(ConstraintViolationException.class);
                }
        );
    }
}
