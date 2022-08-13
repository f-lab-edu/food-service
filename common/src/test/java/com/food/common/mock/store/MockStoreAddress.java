package com.food.common.mock.store;

import com.food.common.address.domain.Address;
import com.food.common.mock.address.MockAddress;
import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreAddress;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockStoreAddress {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private Store store = MockStore.builder().build();
        private Address address = MockAddress.builder().build();
        private String addressDetail = "ABC Building";

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder store(Store store) {
            this.store = store;
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

        public StoreAddress build() {
            return StoreAddress.create(store, address, addressDetail);
        }
    }
}
