package com.food.common.store.domain;

import com.food.common.foodCategory.domain.FoodCategory;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_store")
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    private String address;

    private Integer minOrderAmount;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private LocalTime cookingTime;

    @Enumerated(STRING)
    private Status openStatus;

    private Long foodCategoryId;

    public Store(final String name, final String address, final Integer minOrderAmount,
                 final LocalTime openingTime, final LocalTime closingTime, final LocalTime cookingTime,
                 final Status status, final FoodCategory foodCategory) {
        this.name = name;
        this.address = address;
        this.minOrderAmount = minOrderAmount;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.cookingTime = cookingTime;
        this.openStatus = status;
        this.foodCategoryId = foodCategory.getId();
    }

    public Long getId() {
        return id;
    }

    public enum Status {
        OPEN,
        CLOSED
    }
}
