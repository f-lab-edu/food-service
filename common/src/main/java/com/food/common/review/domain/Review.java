package com.food.common.review.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.order.domain.Order;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity @Table(name = "tb_review")
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private Long userId;

    private Long storeId;

    private Long orderId;

    @Embedded
    private Score score;

    private String content;

    @Embedded
    private ImageUrls ImageUrls;

    public Review(Order order, Score score, String content, ImageUrls imageUrls) {
        this.userId = order.getUserId();
        this.storeId = order.getStoreId();
        this.orderId = order.getId();
        this.score = score;
        this.content = content;
        this.ImageUrls = imageUrls;
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class Score {
        private Byte score;

        public Score(Byte score) {
            validate(score);
            this.score = score;
        }

        private void validate(Byte score) {
            if (0 <= score && score <= 10) return;

            throw new IllegalArgumentException();
        }

        public Byte get() {
            return score;
        }
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class ImageUrls {
        private String imageUrls;

        public ImageUrls(List<String> urls) {
            if (CollectionUtils.isEmpty(urls)) return;

            this.imageUrls = mapToString(urls);
        }

        private String mapToString(List<String> urls) {
            return urls.toString();
        }

        public List<String> get() {
            String[] parsedUrls = imageUrls.replace("[", "")
                    .replace("]", "")
                    .split(",");

            return Arrays.stream(parsedUrls)
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }
}
