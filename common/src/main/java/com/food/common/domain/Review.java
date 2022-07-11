package com.food.common.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity @Table(name = "tb_review")
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private Long userId;

    private Long storeId;

    private Byte score;

    private String content;

    private String ImageUrls;
}
