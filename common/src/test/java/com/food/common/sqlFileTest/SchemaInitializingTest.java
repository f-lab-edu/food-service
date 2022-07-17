package com.food.common.sqlFileTest;

import com.food.common.CommonApplication;
import com.food.common.foodCategory.repository.FoodCategoryRepository;
import com.food.common.store.repository.StoreRepository;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CommonApplication.class)
public class SchemaInitializingTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void shouldBeInitializedUserData() {
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    void shouldBeInitializeFoodCategoryData() {
        assertEquals(7, foodCategoryRepository.findAll().size());
    }

    @Test
    void shouldBeInitializeStoreData() {
        assertEquals(3, storeRepository.findAll().size());
    }
}
