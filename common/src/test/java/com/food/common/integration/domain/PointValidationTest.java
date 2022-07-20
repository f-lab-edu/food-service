package com.food.common.integration.domain;

import com.food.common.common.domain.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<Point>> getViolationsOfPointConstructor(Integer amount) throws NoSuchMethodException {
        Constructor<Point> constructor = Point.class.getConstructor(Integer.class);
        return executableValidator.validateConstructorParameters(constructor, new Object[]{amount});
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void whenValidationWithNegativeNumberConstructorParameter_thenCorrectNumberOfViolations(int value) throws NoSuchMethodException {
        Set<ConstraintViolation<Point>> constraintViolations = getViolationsOfPointConstructor(value);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 1000, 10000})
    void whenValidationWithValidNumberConstructorParameter_thenCorrectNumberOfViolations(int value) throws NoSuchMethodException {
        Set<ConstraintViolation<Point>> constraintViolations = getViolationsOfPointConstructor(value);

        assertEquals(constraintViolations.size(), 0);
    }

    @Test
    void whenValidationWithNullConstructorParameter_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Point>> constraintViolations = getViolationsOfPointConstructor(null);
        
        assertEquals(constraintViolations.size(), 1);
    }
}
