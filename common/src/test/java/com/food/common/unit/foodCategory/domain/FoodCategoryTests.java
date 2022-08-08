package com.food.common.unit.foodCategory.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import com.food.common.mock.foodCategory.MockFoodCategory;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.foodCategory.utils.FoodCategoryFailureMessages.BETWEEN_LENGTH_OF_NAME;
import static com.food.common.foodCategory.utils.FoodCategoryFailureMessages.NOT_BLANK_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FoodCategoryTests extends SuperValidationTests<FoodCategory> {

    @Test
    void validateNameInFoodCategory() {
        FoodCategory foodCategoryWithNullName = MockFoodCategory.builder()
                .name(null)
                .build();

        FoodCategory foodCategoryWithBlankName = MockFoodCategory.builder()
                .name(" ")
                .build();

        FoodCategory foodCategoryWithNormalName = MockFoodCategory.builder()
                .name("한식")
                .build();

        String longName = "A".repeat(16);
        FoodCategory foodCategoryWithLongName = MockFoodCategory.builder()
                .name(longName)
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(foodCategoryWithNullName)).containsExactlyInAnyOrder(NOT_BLANK_NAME),
                () -> assertThat(failureMessagesOf(foodCategoryWithBlankName)).containsExactlyInAnyOrder(NOT_BLANK_NAME),
                () -> assertThat(failureMessagesOf(foodCategoryWithNormalName)).isEmpty(),
                () -> assertThat(failureMessagesOf(foodCategoryWithLongName)).containsExactlyInAnyOrder(formatLength(BETWEEN_LENGTH_OF_NAME, 15, 0, longName))
        );
    }
}
