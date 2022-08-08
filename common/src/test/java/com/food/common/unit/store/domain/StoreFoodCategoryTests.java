package com.food.common.unit.store.domain;

import com.food.common.mock.foodCategory.MockFoodCategory;
import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreFoodCategory;
import com.food.common.store.domain.StoreFoodCategory;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessage.StoreFoodCategory.NOT_NULL_FOOD_CATEGORY;
import static com.food.common.store.utils.StoreValidationFailureMessage.StoreFoodCategory.NOT_NULL_STORE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreFoodCategoryTests extends SuperValidationTests<StoreFoodCategory> {

    @Test
    void validateStoreInStoreFoodCategory() {
        StoreFoodCategory foodCategoryWithNullStore = MockStoreFoodCategory.builder()
                .store(null)
                .build();

        StoreFoodCategory foodCategoryWithNormalStore = MockStoreFoodCategory.builder()
                .store(MockStore.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(foodCategoryWithNullStore)).containsExactlyInAnyOrder(NOT_NULL_STORE),
                () -> assertThat(failureMessagesOf(foodCategoryWithNormalStore)).isEmpty()
        );
    }

    @Test
    void validateFoodCategoryInStoreFoodCategory() {
        StoreFoodCategory foodCategoryWithNullStore = MockStoreFoodCategory.builder()
                .foodCategory(null)
                .build();

        StoreFoodCategory foodCategoryWithNormalStore = MockStoreFoodCategory.builder()
                .foodCategory(MockFoodCategory.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(foodCategoryWithNullStore)).containsExactlyInAnyOrder(NOT_NULL_FOOD_CATEGORY),
                () -> assertThat(failureMessagesOf(foodCategoryWithNormalStore)).isEmpty()
        );
    }
}
