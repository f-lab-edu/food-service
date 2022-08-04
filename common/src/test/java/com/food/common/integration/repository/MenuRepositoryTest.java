package com.food.common.integration.repository;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.repository.MenuRepository;
import com.food.common.store.domain.Store;
import com.food.common.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuRepositoryTest extends CommonRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    void shouldSaveMenu() {
        Optional<Store> store = storeRepository.findById(1L);

        Menu.ImageUrls imageUrls = new Menu.ImageUrls(List.of("https://imageUrlA.jpg", "https://imageUrlB.jpg"));

        Menu menu = new Menu(store.get(), "menuA", 12000, imageUrls);
        menuRepository.save(menu);

        assertThat(menu).isNotNull();
        assertThat(menu.getId()).isEqualTo(4L);
    }
}
