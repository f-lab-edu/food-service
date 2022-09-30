package com.food.mock.user;

import com.food.common.user.domain.User;

public class MockUser {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String nickname = "TestUser";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public User build() {
            return User.create(nickname);
        }
    }
}
