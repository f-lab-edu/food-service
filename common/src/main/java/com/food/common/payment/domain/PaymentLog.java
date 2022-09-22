package com.food.common.payment.domain;

import com.food.common.basetime.BaseTimeEntity;
import com.food.common.user.domain.Point;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.food.common.payment.utils.PaymentValidationFailureMessages.PaymentLog.*;
import static javax.persistence.EnumType.*;
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
    @NotNull(message = PAYMENT_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Comment("결제 수단")
    @NotNull(message = METHOD_CANNOT_BE_NULL)
    @Enumerated(STRING)
    private Method method;

    @Comment("결제 타입")
    @NotNull(message = TYPE_CANNOT_BE_NULL)
    @Enumerated(STRING)
    private Type type;

    @Comment("결제 금액")
    @PositiveOrZero(message = AMOUNT_HAS_TO_BE_POSITIVE)
    @NotNull(message = AMOUNT_CANNOT_BE_NULL)
    private Integer amount;

    @Comment("사용 포인트")
    @NotNull(message = POINT_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    public static PaymentLog create(Payment payment, Method method, Type type, Integer amount, Point point) {
        PaymentLog paymentLog = new PaymentLog();
        paymentLog.payment = payment;
        paymentLog.method = method;
        paymentLog.type = type;
        paymentLog.amount = amount;
        paymentLog.point = point;

        return paymentLog;
    }

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
