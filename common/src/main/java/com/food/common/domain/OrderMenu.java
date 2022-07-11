package com.food.common.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity @Table(name = "tb_order_menu")
public class OrderMenu {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    private Long orderId;

    private Long menuId;

    private String menuOptions;

    private Integer amount;

    private Short count;
}
