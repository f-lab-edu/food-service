package com.food.common.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity @Table(name = "tb_payment")
public class Payment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Integer amount;

    private Method method;

    private Status status;

    private LocalDateTime canceledDate;

    private Integer beforePoint;

    private Integer afterPoint;

    public enum Status {
        payment_requested,
        payment_completed,
        cancellation_requested,
        cancellation_completed
    }

    public enum Method {
        card,
        account_transfer
    }
}
