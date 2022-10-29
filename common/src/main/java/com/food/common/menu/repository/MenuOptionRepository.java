package com.food.common.menu.repository;

import com.food.common.menu.domain.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
