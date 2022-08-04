package com.food.common.integration.domain;

import com.food.common.common.domain.Point;
import com.food.common.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<User>> getViolationsOfUserConstructor(String userId, String password, String nickname, Point point) throws NoSuchMethodException {
        Constructor<User> constructor = User.class.getConstructor(String.class, String.class, String.class, Point.class);

        Object[] parameterValues = {userId, password, nickname, point};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("User 생성자에 UserId를 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankUserId_thenCorrectNumberOfViolations(String userId) throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor(userId, "passwordABCD", "userA", new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("User 생성자에 UserId를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullUserId_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor(null, "passwordABCD", "userA", new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("User 생성자에 Password를 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankPassword_thenCorrectNumberOfViolations(String password) throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor("userA", password, "userA", new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("User 생성자에 Password를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullPassword_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor("userA", null, "userA", new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("User 생성자에 Nickname을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankNickname_thenCorrectNumberOfViolations(String nickname) throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor("userA", "passwordABCD", nickname, new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("User 생성자에 Nickname을 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullNickname_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor("userA", "passwordABCD", null, new Point(1000));

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("User 생성자에 Point를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullPoint_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<User>> constraintViolations =
                getViolationsOfUserConstructor("userA", "passwordABCD", "userA", null);

        assertEquals(constraintViolations.size(), 1);
    }
}
