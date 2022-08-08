package com.food.common.mock.store;

import com.food.common.mock.MockUser;
import com.food.common.user.domain.StoreOwner;
import com.food.common.user.domain.User;

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
