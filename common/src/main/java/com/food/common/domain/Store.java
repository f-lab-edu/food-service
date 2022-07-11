package com.food.common.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

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

    private Status status;

    private Long foodCategoryId;

    public enum Status {
        open,
        closed
    }
}
