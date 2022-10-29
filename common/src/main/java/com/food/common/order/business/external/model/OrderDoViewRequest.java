package com.food.common.order.business.external.model;

import lombok.Getter;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Validated
public class OrderDoViewRequest {
    @NotNull @Positive
    private final Long storeId;
    @NotNull @Size(min = 1)
    private final List<MenuRequest> menus;
    private final String comment;

    public OrderDoViewRequest(Long storeId, List<MenuRequest> menus, String comment) {
        this.storeId = storeId;
        this.menus = menus;
        this.comment = comment;
    }

    public Long getStoreId() {
        return storeId;
    }

    public List<Long> getMenuIds() {
        return menus.stream()
                .map(menu -> menu.menuId)
                .collect(Collectors.toList());
    }

    public MenuRequest getMenuById(Long menuId) {
        return menus.stream()
                .filter(menu -> menu.menuId.equals(menuId))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("메뉴 주문내역이 존재하지 않습니다."));
    }

    public String getComment() {
        return comment;
    }

    @Getter
    public static class MenuRequest {
        @NotNull @Positive
        private final Long menuId;
        @NotNull @Positive @Max(100)
        private final Byte count;
        private final List<MenuOptionRequest> options;

        public MenuRequest(Long menuId, Byte count, List<MenuOptionRequest> options) {
            this.menuId = menuId;
            this.count = count;
            this.options = options;
        }

        public List<MenuOptionRequest> getOptions() {
            return CollectionUtils.isEmpty(options) ? Collections.emptyList() : options;
        }
    }

    @Getter
    public static class MenuOptionRequest {
        @NotNull @Positive
        private final Long optionId;

        @NotNull @Size(min = 1)
        private final List<Long> selectionIds;

        public MenuOptionRequest(Long optionId, List<Long> selectionIds) {
            this.optionId = optionId;
            this.selectionIds = selectionIds;
        }
    }
}
