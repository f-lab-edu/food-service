package com.food.common.order.domain;

import com.food.common.menu.domain.MenuSelection;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_order_menu_selection")
@Entity
public class OrderMenuSelection {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_menu_selection_id")
    private Long id;

    @Comment("주문메뉴")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_menu_id")
    private OrderMenu orderMenu;

    @Comment("주문메뉴 선택옵션 선택지")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "menu_select_id")
    private MenuSelection menuSelection;

    public static OrderMenuSelection create(OrderMenu orderMenu, MenuSelection menuSelection) {
        OrderMenuSelection orderMenuSelection = new OrderMenuSelection();
        orderMenuSelection.orderMenu = orderMenu;
        orderMenuSelection.menuSelection = menuSelection;

        return orderMenuSelection;
    }
}
