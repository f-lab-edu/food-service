package com.food.common.mock.menu;

import com.food.common.menu.domain.Menu;
import com.food.common.mock.store.MockStore;
import com.food.common.store.domain.Store;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockMenu {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private Store store = MockStore.builder().build();
        private String name = "SpicyMenu";
        private Integer amount = 10000;
        private Integer cookingTime = 30;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder cookingTime(Integer cookingTime) {
            this.cookingTime = cookingTime;
            return this;
        }

        public Menu build() {
            return Menu.create(store, name, amount, cookingTime);
        }
    }
}
