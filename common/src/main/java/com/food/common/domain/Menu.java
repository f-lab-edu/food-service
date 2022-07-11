package com.food.common.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity @Table(name = "tb_menu")
public class Menu {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long menuId;

    private Long storeId;

    private String name;

    private Integer amount;

    private String imageUrls;
}
