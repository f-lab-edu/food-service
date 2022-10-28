package com.food.common.menu.business.internal.dto;

import com.food.common.menu.domain.MenuOption;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class MenuOptionDto {
    private final Long id;
    private final Long menuId;
    private final String name;
    private final Byte minSize;
    private final Byte maxSize;
    private final List<MenuSelectionDto> selections;

    public MenuOptionDto(MenuOption entity) {
        id = entity.getId();
        menuId = entity.getMenuId();
        name = entity.getName();
        minSize = entity.getMinSize();
        maxSize = entity.getMaxSize();
        selections = entity.getSelections()
                .stream().map(MenuSelectionDto::new)
                .collect(Collectors.toList());
    }

    public MenuSelectionDto getSelection(Long selectionId) {
        return selections.stream()
                .filter(selection -> selection.hasSameIdAs(selectionId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선택지입니다."));
    }

    public boolean hasSameIdAs(Long optionId) {
        return id.equals(optionId);
    }
}
