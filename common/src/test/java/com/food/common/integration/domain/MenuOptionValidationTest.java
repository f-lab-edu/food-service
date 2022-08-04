package com.food.common.integration.domain;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static com.food.common.integration.domain.MenuValidationTest.createDummyStore;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuOptionValidationTest extends DomainValidationIntegrationTest {

    private Set<ConstraintViolation<MenuOption>> getViolationsOfMenuOptionConstructor(Menu menu, String title, String selection, Integer additionalAmount, Boolean isRequired) throws NoSuchMethodException {
        Constructor<MenuOption> constructor = MenuOption.class.getConstructor(Menu.class, String.class, String.class, Integer.class, Boolean.class);

        Object[] parameterValues = {menu, title, selection, additionalAmount, isRequired};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("MenuOption 생성자에 Store를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullMenu_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(null, "맛 선택", "아주 매운맛", 0, true);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("MenuOption 생성자에 Title를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullTitle_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(createDummyMenu(), null, "아주 매운맛", 0, true);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("MenuOption 생성자에 Title을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankTitle_thenCorrectNumberOfViolations(String title) throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(createDummyMenu(), title, "아주 매운맛", 0, true);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("MenuOption 생성자에 Selection를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullSelection_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(createDummyMenu(), "맛 선택", null, 0, true);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("MenuOption 생성자에 Selection을 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankSelection_thenCorrectNumberOfViolations(String selection) throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(createDummyMenu(), "맛 선택", selection, 0, true);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("MenuOption 생성자에 IsRequired를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullIsRequired_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<MenuOption>> constraintViolations =
                getViolationsOfMenuOptionConstructor(createDummyMenu(), "맛 선택", "아주 매운맛", 0, null);

        assertEquals(constraintViolations.size(), 1);
    }

    private Menu createDummyMenu() {
        return new Menu(createDummyStore(), "Menu A", 12000, null);
    }
}
