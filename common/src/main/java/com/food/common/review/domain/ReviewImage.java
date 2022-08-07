package com.food.common.review.domain;

import com.food.common.image.domain.Image;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Table(name = "tb_review_image")
@Entity
public class ReviewImage {

    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    public static class MultiplePk implements Serializable {
        @Column(name = "review_id")
        private Long reviewId;

        @Column(name = "image_id")
        private Long imageId;

        public MultiplePk(Long reviewId, Long imageId) {
            this.reviewId = reviewId;
            this.imageId = imageId;
        }
    }

    @EmbeddedId
    private MultiplePk pk = new MultiplePk();

    @Comment("리뷰")
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id", insertable = false, updatable = false)
    private Review review;

    @Comment("이미지")
    @NotNull
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    public static ReviewImage create(Review review, Image image) {
        ReviewImage reviewImage = new ReviewImage();
        reviewImage.review = review;
        reviewImage.image = image;

        return reviewImage;
    }
}
