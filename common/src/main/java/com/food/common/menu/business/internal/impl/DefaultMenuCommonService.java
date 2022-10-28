package com.food.common.menu.business.internal.impl;

import com.food.common.menu.business.internal.MenuCommonService;
import com.food.common.menu.business.internal.dto.MenuDto;
import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.repository.MenuOptionRepository;
import com.food.common.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultMenuCommonService implements MenuCommonService {
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    public List<MenuDto> findMenusInIds(List<Long> ids) {
        List<Menu> menus = menuRepository.findMenusByIdIn(ids);
        validateMenus(menus, ids);

        List<MenuOption> menuOptions = menuOptionRepository.findMenuOptionsByMenuIdIn(ids);

        Map<Long, List<MenuOption>> menuOptionMap = menuOptions.stream()
                .collect(Collectors.groupingBy(MenuOption::getMenuId, Collectors.toList()));

        return menus.stream()
                .map(menu -> new MenuDto(menu, menuOptionMap.get(menu.getId())))
                .collect(Collectors.toList());
    }

    private void validateMenus(List<Menu> menus, List<Long> findMenuIds) {
        if (CollectionUtils.isEmpty(menus)) {
            throw new IllegalArgumentException("메뉴를 찾을 수 없습니다.");
        }

        if (menus.size() != findMenuIds.size()) {
            throw new IllegalArgumentException("메뉴를 모두 찾을 수 없습니다.");
        }
    }
}
