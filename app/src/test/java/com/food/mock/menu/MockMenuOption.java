package com.food.mock.menu;

import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

public class MockMenuOption {
    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static class Builder {
        private Long id;
        private Menu menu = MockMenu.builder().build();
        private String name = "사리 추가";
        private Byte minSize = 0;
        private Byte maxSize = 3;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder menu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder minSize(Byte minSize) {
            this.minSize = minSize;
            return this;
        }

        public Builder maxSize(Byte maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public MenuOption build() {
            return MenuOption.create(menu, name, minSize, maxSize);
        }
    }
}
