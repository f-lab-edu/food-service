package com.food.common.menu.repository;

import com.food.common.menu.domain.Menu;

import java.util.List;

public interface MenuQueryRepository {
    List<Menu> findMenusByIdIn(List<Long> menuIds);
}
