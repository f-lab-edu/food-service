package com.food.common.unit.store.domain;

import com.food.common.mock.image.MockImage;
import com.food.common.mock.store.MockStore;
import com.food.common.mock.store.MockStoreImage;
import com.food.common.store.domain.StoreImage;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

import static com.food.common.store.utils.StoreValidationFailureMessages.StoreImage.IMAGE_CANNOT_BE_NULL;
import static com.food.common.store.utils.StoreValidationFailureMessages.StoreImage.STORE_CANNOT_BE_NULL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StoreImageTests extends SuperValidationTests<StoreImage> {

    @Test
    void validateImageInStoreImage() {
        StoreImage storeImageWithNullImage = MockStoreImage.builder()
                .image(null)
                .build();

        StoreImage storeImageWithNormalImage = MockStoreImage.builder()
                .image(MockImage.builder().path("/store/1").build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(storeImageWithNullImage)).containsExactlyInAnyOrder(IMAGE_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(storeImageWithNormalImage)).isEmpty()
        );
    }

    @Test
    void validateStoreInStoreImage() {
        StoreImage storeImageWithNullImage = MockStoreImage.builder()
                .store(null)
                .build();

        StoreImage storeImageWithNormalImage = MockStoreImage.builder()
                .store(MockStore.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(storeImageWithNullImage)).containsExactlyInAnyOrder(STORE_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(storeImageWithNormalImage)).isEmpty()
        );
    }
}
