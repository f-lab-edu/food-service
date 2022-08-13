package com.food.common.mock.foodCategory;

import com.food.common.foodCategory.domain.FoodCategory;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockFoodCategory {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private String name = "한식";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public FoodCategory build() {
            return FoodCategory.create(name);
        }
    }
}
