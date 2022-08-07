package com.food.common.payment.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.user.domain.Point;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_payment_log")
@Entity
public class PaymentLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "payment_log_id")
    private Long id;

    @Comment("결제")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Comment("결제 수단")
    @NotNull
    private Method method;

    @Comment("결제 타입")
    @NotNull
    private Type type;

    @Comment("결제 금액")
    @PositiveOrZero
    @NotNull
    private Integer amount;

    @Comment("사용 포인트")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    public enum Method {
        CARD("카드"),
        ACCOUNT_TRANSFER("계좌 이체"),
        POINT("포인트")
        ;

        private final String description;

        Method(String description) {
            this.description = description;
        }
    }

    public enum Type {
        PAYMENT("결제"),
        CANCELLATION("취소")
        ;

        private final String description;

        Type(String description) {
            this.description = description;
        }
    }
}
