package com.food.common.domain;

import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity @Table(name = "tb_order_menu")
public class OrderMenu {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    private Long orderId;

    private Long menuId;

    @Embedded
    private MenuOptions menuOptions;

    private Integer amount;

    private Short count;

    public OrderMenu(Order order, Menu menu, MenuOptions menuOptions, Integer amount, Short count) {
        this.orderId = order.getId();
        this.menuId = menu.getId();
        this.menuOptions = menuOptions;
        this.amount = amount;
        this.count = count;
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class MenuOptions {
        private String menuOptions;

        public MenuOptions(List<MenuOption> menuOptions) {
            this.menuOptions = mapToString(menuOptions);
        }

        private String mapToString(List<MenuOption> menuOptions) {
            if (CollectionUtils.isEmpty(menuOptions)) return null;

            StringBuilder result = new StringBuilder();
            menuOptions.forEach(menuOption ->
                    result.append(String.format(
                            "%s: %s", menuOption.getTitle(), menuOption.getSelection())
                    ));

            return result.toString();
        }
    }
}
