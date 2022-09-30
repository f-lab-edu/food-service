package com.food.mock.store;

import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;
import com.food.mock.user.MockUser;

public class MockStoreOwner {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private User user = MockUser.builder()
                .nickname("imowner")
                .build();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public StoreOwner build() {
            return StoreOwner.create(user);
        }
    }
}
