package com.food.common.order.domain;

import com.food.common.menu.domain.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.food.common.order.utils.OrderValidationFailureMessages.OrderMenu.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_order_menu")
@Entity
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    @Comment("주문")
    @NotNull(message = ORDER_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("메뉴")
    @NotNull(message = MENU_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Comment("금액")
    @NotNull(message = AMOUNT_CANNOT_BE_NULL)
    @PositiveOrZero(message = AMOUNT_HAS_TO_BE_POSITIVE)
    private Integer amount;

    @Comment("수량")
    @NotNull(message = COUNT_CANNOT_BE_NULL)
    @Max(value = 100, message = COUNT_HAS_TO_BE_LESS_THAN_MAX_VALUE)
    private Byte count;

    public static OrderMenu create(Order order, Menu menu, Integer amount, Byte count) {
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.order = order;
        orderMenu.menu = menu;
        orderMenu.amount = amount;
        orderMenu.count = count;

        return orderMenu;
    }

    public Long getOrderId() {
        return order.getId();
    }

    public Long getMenuId() {
        return menu.getId();
    }
}
