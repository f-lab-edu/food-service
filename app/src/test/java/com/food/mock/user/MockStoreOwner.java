package com.food.mock.user;

import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockStoreOwner {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private User user;

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
