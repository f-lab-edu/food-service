package com.food.common.user.domain;

import com.food.common.basetime.BaseTimeEntity;
import com.food.common.payment.domain.Payment;
import com.food.common.user.enumeration.PointType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.food.common.user.UserValidationFailureMessages.Point.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_point")
@Entity
public class Point extends BaseTimeEntity {
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
    @Enumerated(STRING)
    private PointType type;

    @Comment("포인트 금액")
    @PositiveOrZero(message = CHANGED_AMOUNT_HAS_TO_BE_POSITIVE)
    @NotNull(message = CHANGED_AMOUNT_CANNOT_BE_NULL)
    private Integer changedAmount;

    @Comment("잔여 포인트 금액")
    @PositiveOrZero(message = CURRENT_AMOUNT_HAS_TO_BE_POSITIVE)
    @NotNull(message = CURRENT_AMOUNT_CANNOT_BE_NULL)
    private Integer currentAmount;

    @Comment("결제정보")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public static Point create(User user, PointType type, Integer changedAmount, Integer currentAmount, Payment payment) {
        Point point = new Point();
        point.user = user;
        point.type = type;
        point.changedAmount = changedAmount;
        point.currentAmount = currentAmount;
        point.payment = payment;

        return point;
    }

    public static Point createFirstPoint(User user) {
        Point point = new Point();
        point.user = user;
        point.currentAmount = 0;

        return point;
    }

    public Point use(Integer amount) {
        Point point = new Point();
        point.user = user;
        point.type = PointType.USE;
        point.changedAmount = amount;
        point.currentAmount = this.currentAmount - amount;

        return point;
    }

    public Point recollect(Integer amount) {
        Point point = new Point();
        point.user = user;
        point.type = PointType.RECOLLECT;
        point.changedAmount = amount;
        point.currentAmount = this.currentAmount + amount;

        return point;
    }

    public Point collect(Integer amount, Payment payment) {
        Point point = new Point();
        point.user = user;
        point.type = PointType.COLLECT;
        point.changedAmount = amount;
        point.currentAmount = this.currentAmount + amount;
        point.payment = payment;

        return point;
    }

    public Point retrieve(Integer amount, Payment payment) {
        Point point = new Point();
        point.user = user;
        point.type = PointType.RETRIEVE;
        point.changedAmount = amount;
        point.currentAmount = this.currentAmount - amount;
        point.payment = payment;

        return point;
    }

    public Long getUserId() {
        return user.getId();
    }

    public Long getPaymentId() {
        if (payment == null) return null;

        return payment.getId();
    }
}
