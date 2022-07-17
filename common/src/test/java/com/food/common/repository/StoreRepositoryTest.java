package com.food.common.repository;

import com.food.common.foodCategory.repository.FoodCategoryRepository;
import com.food.common.store.domain.Store;
import com.food.common.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreRepositoryTest extends CommonRepositoryTest{

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void shouldSaveStore() {
        Store store = new Store("storeA", "address 12345", 10000,
                LocalTime.of(10, 00), LocalTime.of(22, 00), LocalTime.of(00, 30),
                Store.Status.OPEN, foodCategoryRepository.findById(1L).get());

        storeRepository.save(store);

        assertThat(store.getId()).isEqualTo(4L);
    }
}
