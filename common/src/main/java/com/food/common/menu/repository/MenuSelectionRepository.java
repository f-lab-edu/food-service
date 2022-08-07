package com.food.common.menu.repository;

import com.food.common.menu.domain.MenuSelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuSelectionRepository extends JpaRepository<MenuSelection, Long> {
}
