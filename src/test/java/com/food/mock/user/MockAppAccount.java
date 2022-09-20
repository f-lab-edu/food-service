package com.food.mock.user;

import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockAppAccount {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private String loginId = "test_user_ABC@email.com";
        private String password = "ABCDabcd1234!@#$";
        private User user = MockUser.builder().build();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public AppAccount build() {
            return AppAccount.create(loginId, password, user);
        }
    }
}
