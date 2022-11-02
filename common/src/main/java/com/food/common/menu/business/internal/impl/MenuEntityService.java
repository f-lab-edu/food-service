package com.food.common.menu.business.internal.impl;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class MenuEntityService {
    private final MenuRepository menuRepository;

    public Map<Long, Menu> findAllByIdIn(List<Long> menuIds) {
        List<Menu> menus = menuRepository.findMenusByIdIn(menuIds);

        Map<Long, Menu> result = menus.stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));
        validate(menuIds, result);

        return result;
    }

    private void validate(List<Long> keys, Map<Long, Menu> findResult) {
        for (Long key : keys) {
            if (!findResult.containsKey(key)) {
                throw new IllegalArgumentException("Menu를 찾을 수 없습니다. menuId=" + key);
            }
        }
    }
}
