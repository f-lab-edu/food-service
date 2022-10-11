package com.food.mock.store;

import com.food.common.foodCategory.domain.FoodCategory;
import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreFoodCategory;
import com.food.mock.foodCategory.MockFoodCategory;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockStoreFoodCategory {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Store store = MockStore.builder().build();
        private FoodCategory foodCategory = MockFoodCategory.builder().build();

        public Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Builder foodCategory(FoodCategory foodCategory) {
            this.foodCategory = foodCategory;
            return this;
        }

        public StoreFoodCategory build() {
            return StoreFoodCategory.create(store, foodCategory);
        }
    }
}
