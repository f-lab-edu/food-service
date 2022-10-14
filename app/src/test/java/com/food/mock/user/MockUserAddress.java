package com.food.mock.user;

import com.food.common.address.domain.Address;
import com.food.common.user.domain.User;
import com.food.common.user.domain.UserAddress;
import com.food.mock.address.MockAddress;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockUserAddress {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private User user = MockUser.builder().build();
        private String name = "우리집";
        private Address address = MockAddress.builder().build();
        private String addressDetail = "가나아파트";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder addressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
            return this;
        }

        public UserAddress build() {
            return UserAddress.create(user, name, address, addressDetail);
        }
    }
}
