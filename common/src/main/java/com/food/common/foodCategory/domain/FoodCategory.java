package com.food.common.foodCategory.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_food_category")
@Entity
public class FoodCategory {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    private String name;

    public FoodCategory(@NotBlank final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
