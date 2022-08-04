package com.food.common.integration.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import com.food.common.menu.domain.Menu;
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

public class MenuValidationTest extends DomainValidationIntegrationTest {
    private Set<ConstraintViolation<Menu>> getViolationsOfMenuConstructor(Store store, String name, Integer amount, Menu.ImageUrls imageUrls) throws NoSuchMethodException {
        Constructor<Menu> constructor = Menu.class.getConstructor(Store.class, String.class, Integer.class, Menu.ImageUrls.class);

        Object[] parameterValues = {store, name, amount, imageUrls};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("Menu 생성자에 Store를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullStore_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Menu>> constraintViolations =
                getViolationsOfMenuConstructor(null, "Menu A", 12000, null);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Menu 생성자에 Name을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankName_thenCorrectNumberOfViolations(String name) throws NoSuchMethodException {
        Set<ConstraintViolation<Menu>> constraintViolations =
                getViolationsOfMenuConstructor(createDummyStore(), name, 12000, null);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Menu 생성자에 Name을 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullName_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Menu>> constraintViolations =
                getViolationsOfMenuConstructor(createDummyStore(), null, 12000, null);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -1000})
    @DisplayName("Menu 생성자에 Amount을 음수로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankAmount_thenCorrectNumberOfViolations(Integer amount) throws NoSuchMethodException {
        Set<ConstraintViolation<Menu>> constraintViolations =
                getViolationsOfMenuConstructor(createDummyStore(), "Menu A", amount, null);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Menu 생성자에 Amount를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullAmount_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Menu>> constraintViolations =
                getViolationsOfMenuConstructor(createDummyStore(), "Menu A", null, null);

        assertEquals(constraintViolations.size(), 1);
    }

    public static Store createDummyStore() {
        return new Store("Store Name A", "dummy address", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00),
                LocalTime.of(00, 30), Store.Status.OPEN, new FoodCategory("한식"));
    }
}
