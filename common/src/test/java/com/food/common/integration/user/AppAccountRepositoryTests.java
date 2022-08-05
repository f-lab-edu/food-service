package com.food.common.integration.user;

import com.food.common.integration.SuperIntegrationTest;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
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

public class AppAccountRepositoryTests extends SuperIntegrationTest {

    @Autowired
    private AppAccountRepository appAccountRepository;

    private User mockUser;

    @BeforeEach
    void setup() {
        mockUser = userFactory.user();
    }

    @DisplayName("AppAccount가 정상적으로 저장된다")
    @Test
    void shouldSaveAppAccount() {
        AppAccount account = AppAccount.create("testUser@email.com", "abcd1234!@#$", mockUser);

        AppAccount savedAccount = appAccountRepository.save(account);
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isNotNull();
    }

    @DisplayName("AppAccount 유효성 실패케이이스를 검증한다")
    @Test
    void shouldBeFailedValidation() {
        assertAll(
                () -> assertAll(Stream.of("", " ", "   ")
                        .map(blankValue -> (Executable) () -> {
                                AppAccount account = AppAccount.create("testUser@email.com", blankValue, mockUser);

                                assertThatThrownBy(() -> appAccountRepository.save(account),
                                        "AppAccount의 Password가 공백일 경우 유효성 검증에 실패한다. value="+blankValue)
                                        .isInstanceOf(ConstraintViolationException.class);
                            })
                        .collect(Collectors.toList())),

                () -> assertAll(Stream.of("", " ", "   ", "testId", "testId1234", "Test1234@.com")
                        .map(value -> (Executable) () -> {
                            AppAccount account = AppAccount.create(value, "abcd1234!@#$", mockUser);

                            assertThatThrownBy(() -> appAccountRepository.save(account),
                                    "AppAccount의 LoginId가 공백이거나 이메일 형식이 아닐 경우 유효성 검증에 실패해야한다. value="+value)
                                    .isInstanceOf(ConstraintViolationException.class);
                        }).collect(Collectors.toList())),

                () -> {
                    AppAccount account = AppAccount.create("testUser@email.com", "abcd1234!@#$", null);

                    assertThatThrownBy(() -> appAccountRepository.save(account),
                            "AppAccount의 User가 null일 경우 유효성 검증에 실패해야한다")
                            .isInstanceOf(ConstraintViolationException.class);
                }
        );
    }
}
