package com.food.common.menu.business.internal.dto;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class MenuDto {
    private final Long id;
    private final Long storeId;
    private final String name;
    private final Integer amount;
    private final Integer cookingTime;
    private final List<MenuOptionDto> options;

    public MenuDto(Menu menuEntity, List<MenuOption> menuOptionEntities) {
        id = menuEntity.getId();
        storeId = menuEntity.getStoreId();
        name = menuEntity.getName();
        amount = menuEntity.getAmount();
        cookingTime = menuEntity.getCookingTime();
        options = CollectionUtils.isEmpty(menuOptionEntities) ?
                Collections.emptyList() :
                menuOptionEntities.stream()
                .map(MenuOptionDto::new)
                .collect(Collectors.toList());
    }


    public MenuOptionDto getOptionById(Long optionId) {
        return options.stream()
                .filter(option -> option.hasSameIdAs(optionId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선택옵션입니다."));
    }
}
