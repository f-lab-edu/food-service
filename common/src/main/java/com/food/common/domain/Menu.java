package com.food.common.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity @Table(name = "tb_menu")
public class Menu {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private Long storeId;

    private String name;

    private Integer amount;

    private String imageUrls;

    public Long getId() {
        return id;
    }

    public Menu(Store store, String name, Integer amount, String imageUrls) {
        this.storeId = store.getId();
        this.name = name;
        this.amount = amount;
        this.imageUrls = imageUrls;
    }
}
