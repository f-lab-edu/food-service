package com.food.common.mock.user;

import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockSocialAccount {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private String loginId = "test_user_ABC@email.com";
        private User user = MockUser.builder().build();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder loginId(String loginId) {
            this.loginId = loginId;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public SocialAccount build() {
            return SocialAccount.create(loginId, user);
        }
    }
}
