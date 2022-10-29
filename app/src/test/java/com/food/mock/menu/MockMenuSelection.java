package com.food.mock.menu;

import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.domain.MenuSelection;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockMenuSelection {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private MenuOption option = MockMenuOption.builder().build();
        private String selection = "우동 사리";
        private Integer amount = 2000;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder option(MenuOption option) {
            this.option = option;
            return this;
        }

        public Builder selection(String selection) {
            this.selection = selection;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public MenuSelection build() {
            return MenuSelection.create(option, selection, amount);
        }
    }
}
