package com.food.common.order.domain;

import com.food.common.basetime.BaseTimeEntity;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.food.common.order.utils.OrderValidationFailureMessages.Order.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_order")
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Comment("구매자")
    @NotNull(message = CUSTOMER_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Comment("가게")
    @NotNull(message = STORE_CANNOT_BE_NULL)
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Comment("주문 금액")
    @NotNull(message = AMOUNT_CANNOT_BE_NULL)
    @PositiveOrZero(message = AMOUNT_HAS_TO_BE_POSITIVE)
    private Integer amount;

    @Comment("주문 상태")
    @NotNull(message = STATUS_CANNOT_BE_NULL)
    @Enumerated(STRING)
    private Status status;

    @Comment("추가 요청 사항")
    @Length(max = 150, message = COMMENT_HAS_TO_BE_BETWEEN_LENGTH)
    private String comment;

    public static Order create(User customer, Store store, Integer amount, Status status, String comment) {
        Order order = new Order();
        order.customer = customer;
        order.store = store;
        order.amount = amount;
        order.status = status;
        order.comment = comment;

        return order;
    }

    public enum Status {
        REQUEST("주문 요청 중"),
        COOKING("조리 중"),
        COMPLETED("조리 완료"),
        CANCELED("취소")
        ;

        private final String description;

        Status(String description) {
            this.description = description;
        }
    }
}
