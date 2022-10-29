package com.food.mock.store;

import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.repository.MenuOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockMenuOptionFactory {

    @Autowired
    private MenuOptionRepository menuOptionRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public MenuOption menuOption(MenuOption menuOption) {
        return menuOptionRepository.save(menuOption);
    }
}
