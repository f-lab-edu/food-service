package com.food.common.payment.domain;

import com.food.common.basetime.BaseTimeEntity;
import com.food.common.order.domain.Order;
import com.food.common.payment.enumeration.PaymentActionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.food.common.payment.utils.PaymentValidationFailureMessages.Payment.ORDER_CANNOT_BE_NULL;
import static com.food.common.payment.utils.PaymentValidationFailureMessages.Payment.TYPE_CANNOT_BE_NULL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_payment")
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Comment("주문")
    @NotNull(message = ORDER_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("결제 상태")
    @NotNull(message = TYPE_CANNOT_BE_NULL)
    private PaymentActionType actionType;

    public static Payment create(Order order, PaymentActionType actionType) {
        Payment payment = new Payment();
        payment.order = order;
        payment.actionType = actionType;

        return payment;
    }

}
