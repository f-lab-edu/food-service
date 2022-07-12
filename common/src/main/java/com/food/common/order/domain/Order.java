package com.food.common.order.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.payment.domain.Payment;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity @Table(name = "tb_order")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Long userId;

    private Long storeId;

    private Integer amount;

    private Status status;

    private String additionalComment;

    private Long paymentId;

    public Order(User user, Long storeId, Integer amount, Status status, String additionalComment, Payment payment) {
        this.userId = user.getUserId();
        this.storeId = storeId;
        this.amount = amount;
        this.status = status;
        this.additionalComment = additionalComment;
        this.paymentId = payment.getId();
    }

    public Long getId() {
        return id;
    }

    public enum Status {
        REQUESTED,
        COOKING,
        COMPLETED,
        CANCELED
    }
}
