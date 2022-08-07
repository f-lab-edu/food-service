package com.food.common.order.domain;

import com.food.common.menu.domain.Menu;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_order_menu")
@Entity
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    @Comment("주문")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("메뉴")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Comment("금액")
    @NotNull
    @PositiveOrZero
    private Integer amount;

    @Comment("수량")
    @NotNull
    @Size(max = 100)
    private Byte count;

    public static OrderMenu create(Order order, Menu menu, Integer amount, Byte count) {
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.order = order;
        orderMenu.menu = menu;
        orderMenu.amount = amount;
        orderMenu.count = count;

        return orderMenu;
    }
}
