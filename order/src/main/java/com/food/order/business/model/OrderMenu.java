package com.food.order.business.model;

import com.food.common.order.business.model.OrderMenuDto;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class OrderMenu {
    private Long menuId;
    private Integer amount;
    private Byte count;
    private List<OrderMenuOption> options;

    @Builder
    public OrderMenu(Long menuId, Integer menuAmount, Byte count, List<OrderMenuOption> options) {
        this.menuId = menuId;
        this.amount = menuAmount;
        this.count = count;
        this.options = options == null ? Collections.emptyList() : options;
    }

    public Integer getOrderMenuAmount() {
        return (amount + totalExtraAmountOfOptions()) * count;
    }

    public Integer totalExtraAmountOfOptions() {
        return options.stream()
                .map(OrderMenuOption::totalAmountOfSelections)
                .reduce(0, Integer::sum);
    }

    public OrderMenuDto toOrderMenuDto() {
        return new OrderMenuDto(menuId, amount, count);
    }

    public static class OrderMenuOption {
        private Long id;

        private Byte maxSize;

        private Byte minSize;

        private List<OrderMenuSelection> selections;

        public OrderMenuOption(Long optionId, Byte maxSize, Byte minSize, List<OrderMenuSelection> selections) {
            this.id = optionId;
            this.maxSize = maxSize;
            this.minSize = minSize;
            this.selections = selections;
            validate();
        }

        private void validate() {
            if (CollectionUtils.isEmpty(selections)) throw new IllegalArgumentException("");
            if (selections.size() < minSize) throw new IllegalArgumentException("최소 선택개수보다 적습니다.");
            if (maxSize < selections.size()) throw new IllegalArgumentException("최대 선택개수보다 더 많습니다.");
        }

        public Integer totalAmountOfSelections() {
            return selections.stream()
                    .map(selection -> selection.amount)
                    .reduce(0, Integer::sum);
        }
    }

    public static class OrderMenuSelection {
        private Long id;
        private Integer amount;

        public OrderMenuSelection(Long selectionId, Integer amount) {
            this.id = selectionId;
            this.amount = amount;
        }
    }
}
