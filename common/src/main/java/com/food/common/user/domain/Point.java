package com.food.common.user.domain;

import com.food.common.payment.domain.Payment;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.food.common.user.UserValidationFailureMessages.Point.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_point")
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @Comment("유저")
    @NotNull(message = USER_CANNOT_BE_NULL)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("적립/사용 타입")
    @NotNull(message = TYPE_CANNOT_BE_NULL)
    private Type type;

    @Comment("포인트 금액")
    @PositiveOrZero(message = CHANGED_AMOUNT_HAS_TO_BE_POSITIVE)
    @NotNull(message = CHANGED_AMOUNT_CANNOT_BE_NULL)
    private Integer changedAmount;

    @Comment("잔여 포인트 금액")
    @PositiveOrZero(message = CURRENT_AMOUNT_HAS_TO_BE_POSITIVE)
    @NotNull(message = CURRENT_AMOUNT_CANNOT_BE_NULL)
    private Integer currentAmount;

    @Comment("결제정보")
    @NotNull(message = PAYMENT_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public static Point create(User user, Type type, Integer changedAmount, Integer currentAmount, Payment payment) {
        Point point = new Point();
        point.user = user;
        point.type = type;
        point.changedAmount = changedAmount;
        point.currentAmount = currentAmount;
        point.payment = payment;

        return point;
    }

    public enum Type {
        COLLECT("적립"),
        USE("사용")
        ;

        private final String description;

        Type(String description) {
            this.description = description;
        }
    }
}