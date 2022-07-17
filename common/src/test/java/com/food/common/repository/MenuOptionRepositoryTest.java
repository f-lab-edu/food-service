package com.food.common.repository;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.repository.MenuOptionRepository;
import com.food.common.menu.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuOptionRepositoryTest extends CommonRepositoryTest {
    @Autowired
    private MenuOptionRepository menuOptionRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    void shouldSaveMenuOptions() {
        Optional<Menu> menu = menuRepository.findById(1L);
        MenuOption menuOption = new MenuOption(menu.get(), "맛 선택", "가장 매운맛", 0, true);

        menuOptionRepository.save(menuOption);

        assertThat(menuOption).isNotNull();
    }
}
