package com.food.common.domain;

import com.food.common.CommonApplication;
import com.food.common.repository.FoodCategoryRepository;
import com.food.common.repository.UserRepository;
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

    @Test
    void shouldBeInitializedUserData() {
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    void shouldBeInitializeFoodCategoryData() {
        assertEquals(7, foodCategoryRepository.findAll().size());
    }
}
