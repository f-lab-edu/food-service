package com.food.mock.store;

import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreOperatingTime;

import java.time.LocalTime;

public class MockStoreOperatingTime {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Store store = MockStore.builder().build();
        private StoreOperatingTime.Week week = StoreOperatingTime.Week.MON;
        private LocalTime openingTime = LocalTime.of(10, 30);
        private LocalTime closingTime = LocalTime.of(22, 30);

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Builder week(StoreOperatingTime.Week week) {
            this.week = week;
            return this;
        }

        public Builder openingTime(LocalTime openingTime) {
            this.openingTime = openingTime;
            return this;
        }

        public Builder closingTime(LocalTime closingTime) {
            this.closingTime = closingTime;
            return this;
        }

        public StoreOperatingTime build() {
            return StoreOperatingTime.create(store, week, openingTime, closingTime);
        }
    }
}
