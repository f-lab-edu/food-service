package com.food.common.mock.store;

import com.food.common.image.domain.Image;
import com.food.common.mock.image.MockImage;
import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreImage;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockStoreImage {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Store store = MockStore.builder().build();
        private Image image = MockImage.builder().build();

        public Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public StoreImage build() {
            return StoreImage.create(store, image);
        }
    }
}
