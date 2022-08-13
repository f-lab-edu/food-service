package com.food.common.review.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.order.domain.Order;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.User;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_review")
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Comment("작성자")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @Comment("가게")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Comment("주문")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Comment("평점")
    @Size(max = 10)
    @NotNull
    private Byte score;

    @Comment("내용")
    @Length(max = 1000)
    @NotBlank
    private String content;

    public static Review create(User writer, Store store, Order order, Byte score, String content) {
        Review review = new Review();
        review.writer = writer;
        review.store = store;
        review.order = order;
        review.score = score;
        review.content = content;

        return review;
    }
}
