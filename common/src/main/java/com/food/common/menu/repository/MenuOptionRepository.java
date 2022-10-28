package com.food.common.menu.repository;

import com.food.common.menu.domain.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {

    @Query("select option " +
            "from MenuOption option " +
            "left join fetch option.selections " +
            "where option.menu.id in (:menuIds)")
    List<MenuOption> findMenuOptionsByMenuIdIn(@Param("menuIds") List<Long> menuIds);
}
