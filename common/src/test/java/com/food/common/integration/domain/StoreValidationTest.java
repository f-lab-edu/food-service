package com.food.common.integration.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import com.food.common.store.domain.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.time.LocalTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<Store>> getViolationsOfStoreConstructor(String name, String address, Integer minOrderAmount,
                                                                           LocalTime openingTime, LocalTime closingTime, LocalTime cookingTime,
                                                                           Store.Status status, FoodCategory foodCategory) throws NoSuchMethodException {
        Constructor<Store> constructor = Store.class.getConstructor(
                String.class, String.class, Integer.class, LocalTime.class, LocalTime.class, LocalTime.class, Store.Status.class, FoodCategory.class);

        Object[] parameterValues = {name, address, minOrderAmount, openingTime, closingTime, cookingTime, status, foodCategory};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Store 생성자에 Name을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankName_thenCorrectNumberOfViolations(String name) throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                name, "dummy address", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 Name을 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullName_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations =
                getViolationsOfStoreConstructor(
                        null, "dummy address", 10000,
                        LocalTime.of(10, 00), LocalTime.of(22, 00),
                        LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
                );

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Store 생성자에 Address을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankAddress_thenCorrectNumberOfViolations(String address) throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", address, 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 Address을 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullAddress_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations =
                getViolationsOfStoreConstructor(
                        "Store Name A", null, 10000,
                        LocalTime.of(10, 00), LocalTime.of(22, 00),
                        LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
                );

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -10000})
    @DisplayName("Store 생성자에 MinOrderAmount을 음수로 넣으면 유효성 검증에 실패한다.")
    void validationWithNegativeMinOrderAmount_thenCorrectNumberOfViolations(int minOrderAmount) throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", minOrderAmount,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 MinOrderAmount을 Null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullMinOrderAmount_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", null,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 OpeningTime을 ClosingTime 이후 값으로 넣으면 유효성 검증에 실패한다.")
    void validationWithOpeningTimeValueAfterClosingTime_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", 10000,
                LocalTime.of(22, 00), LocalTime.of(10, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 CookingTime을 Null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullCookingTime_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                null, Store.Status.OPEN, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 Status를 Null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullStatus_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), null, new FoodCategory("한식")
        );

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Store 생성자에 FoodCategory를 Null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullFoodCategory_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Store>> constraintViolations = getViolationsOfStoreConstructor(
                "Store Name A", "dummy address", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, null
        );

        assertEquals(constraintViolations.size(), 1);
    }
}
