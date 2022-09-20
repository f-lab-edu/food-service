package com.food.mock.order;

import com.food.common.menu.domain.Menu;
import com.food.common.mock.menu.MockMenu;
import com.food.common.order.domain.Order;
import com.food.common.order.domain.OrderMenu;

public class MockOrderMenu {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Order order = MockOrder.builder().build();
        private Menu menu = MockMenu.builder().build();
        private Integer amount = 30000;
        private Byte count = 3;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder menu(Menu menu) {
            this.menu = menu;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder count(Byte count) {
            this.count = count;
            return this;
        }

        public OrderMenu build() {
            return OrderMenu.create(order, menu, amount, count);
        }
    }
}
