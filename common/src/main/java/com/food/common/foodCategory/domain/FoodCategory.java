package com.food.common.foodCategory.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Table(name = "tb_food_category")
@Entity
public class FoodCategory {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }
}
