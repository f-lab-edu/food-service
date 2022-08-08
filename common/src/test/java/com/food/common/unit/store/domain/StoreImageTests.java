package com.food.common.unit.store.domain;

import com.food.common.mock.store.MockStoreImage;
import com.food.common.store.domain.StoreImage;
import com.food.common.unit.SuperValidationTests;
import org.junit.jupiter.api.Test;

public class StoreImageTests extends SuperValidationTests<StoreImage> {

    @Test
    void validateImageInStoreImage() {
        MockStoreImage.builder().build();

        
    }
}
