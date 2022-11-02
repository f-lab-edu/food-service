package com.food.common.menu.business.internal.impl;

import com.food.common.menu.business.internal.MenuCommonService;
import com.food.common.menu.business.internal.dto.MenuDto;
import com.food.common.menu.domain.Menu;
import com.food.common.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultMenuCommonService implements MenuCommonService {
    private final MenuRepository menuRepository;

    public List<MenuDto> findMenusInIds(List<Long> ids) {
        List<Menu> menus = menuRepository.findMenusByIdIn(ids);
        validateMenus(menus, ids);

        return menus.stream()
                .map(MenuDto::new)
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
