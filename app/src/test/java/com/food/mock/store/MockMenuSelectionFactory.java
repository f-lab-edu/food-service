package com.food.mock.store;

import com.food.common.menu.domain.MenuSelection;
import com.food.common.menu.repository.MenuSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;

@TestComponent
public class MockMenuSelectionFactory {

    @Autowired
    private MenuSelectionRepository menuSelectionRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public List<MenuSelection> menuSelections(MenuSelection... menuSelections) {
        return menuSelectionRepository.saveAll(List.of(menuSelections));
    }
}
