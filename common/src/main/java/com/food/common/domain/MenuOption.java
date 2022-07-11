package com.food.common.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "tb_menu_option")
public class MenuOption {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long menuOptionId;

    private Long menuId;

    private String title;

    private String selection;

    private Integer additionalAmount;

    private Boolean isRequired;
}
