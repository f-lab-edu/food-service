package com.food.common.integration.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodCategoryValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<FoodCategory>> getViolationsOfUserConstructor(String name) throws NoSuchMethodException {
        Constructor<FoodCategory> constructor = FoodCategory.class.getConstructor(String.class);

        Object[] parameterValues = {name};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("FoodCategory 생성자에 Name을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankName_thenCorrectNumberOfViolations(String name) throws NoSuchMethodException {
        Set<ConstraintViolation<FoodCategory>> constraintViolations = getViolationsOfUserConstructor(name);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("FoodCategory 생성자에 Name을 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullName_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<FoodCategory>> constraintViolations = getViolationsOfUserConstructor(null);

        assertEquals(constraintViolations.size(), 1);
    }
}
