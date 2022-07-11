package com.food.common.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity @Table(name = "tb_order")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long userId;

    private Long storeId;

    private Integer amount;

    private Status status;

    private String additionalComment;

    private Long paymentId;

    public enum Status {
        requested,
        cooking,
        completed,
        canceled
    }
}
