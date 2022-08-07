package com.food.common.user.domain;

import com.food.common.payment.domain.Payment;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Comment("적립/사용 타입")
    @NotNull
    private Type type;

    @Comment("포인트 금액")
    @PositiveOrZero
    @NotNull
    private Integer changedAmount;

    @Comment("잔여 포인트 금액")
    @PositiveOrZero
    @NotNull
    private Integer currentAmount;

    @Comment("결제정보")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

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
