package com.food.common.order.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.payment.domain.Payment;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
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

    @Enumerated(STRING)
    private Status status;

    private String additionalComment;

    private Long paymentId;

    public Order(User user, Store store, Integer amount, Status status, String additionalComment, Payment payment) {
        this.userId = user.getUserId();
        this.storeId = store.getId();
        this.amount = amount;
        this.status = status;
        this.additionalComment = additionalComment;
        this.paymentId = payment.getId();
    }

    public Long getId() {
        return id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public enum Status {
        REQUESTED,
        COOKING,
        COMPLETED,
        CANCELED
    }
}
