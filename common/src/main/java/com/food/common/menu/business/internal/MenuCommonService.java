package com.food.common.menu.business.internal;

import com.food.common.menu.business.internal.dto.MenuDto;

import java.util.List;

public interface MenuCommonService {
    List<MenuDto> findMenusInIds(List<Long> ids);
}
