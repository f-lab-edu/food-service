package com.food.common.unit.store.domain;

import com.food.common.mock.foodCategory.MockFoodCategory;
import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreFoodCategory;
import com.food.common.store.domain.StoreFoodCategory;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreFoodCategory.FOOD_CATEGORY_CANNOT_BE_NULL;
import static com.food.common.store.utils.StoreValidationFailureMessages.StoreFoodCategory.STORE_CANNOT_BE_NULL;
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
                () -> assertThat(failureMessagesOf(foodCategoryWithNullStore)).containsExactlyInAnyOrder(STORE_CANNOT_BE_NULL),
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
                () -> assertThat(failureMessagesOf(foodCategoryWithNullStore)).containsExactlyInAnyOrder(FOOD_CATEGORY_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(foodCategoryWithNormalStore)).isEmpty()
        );
    }
}
