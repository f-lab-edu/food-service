package com.food.mock.store;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockMenuFactory {

    @Autowired
    private MenuRepository menuRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Menu menu(Menu menu) {
        return menuRepository.save(menu);
    }
}
