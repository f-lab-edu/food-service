package com.food.common.mock.store;

import com.food.common.mock.MockUser;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.StoreOwner;

public class MockStore {
    public static Builder builder()  {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name = "AStore";
        private StoreOwner owner = MockStoreOwner.builder()
                .user(MockUser.builder()
                .nickname("AStoreOwnerNN")
                .build())
                .build();
        private Integer minOrderAmount = 10000;
        private Store.OpenStatus status = Store.OpenStatus.OPEN;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder owner(StoreOwner owner) {
            this.owner = owner;
            return this;
        }

        public Builder minOrderAmount(Integer minOrderAmount) {
            this.minOrderAmount = minOrderAmount;
            return this;
        }

        public Builder status(Store.OpenStatus status) {
            this.status = status;
            return this;
        }

        public Store build() {
            return Store.create(name, owner, minOrderAmount, status);
        }
    }
}
