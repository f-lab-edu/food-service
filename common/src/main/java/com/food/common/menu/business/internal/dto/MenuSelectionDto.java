package com.food.common.menu.business.internal.dto;

import com.food.common.menu.domain.MenuSelection;
import lombok.Getter;

@Getter
public final class MenuSelectionDto {
    private final Long id;
    private final Long optionId;
    private final String selection;
    private final Integer amount;

    public MenuSelectionDto(MenuSelection entity) {
        id = entity.getId();
        optionId = entity.getOptionId();
        selection = entity.getSelection();
        amount = entity.getAmount();
    }

    public boolean hasSameIdAs(Long id) {
        return this.id.equals(id);
    }
}
