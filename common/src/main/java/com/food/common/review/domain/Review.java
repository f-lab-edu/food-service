package com.food.common.review.domain;

import com.food.common.common.domain.BaseTimeEntity;
import com.food.common.order.domain.Order;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    public Review(@NotNull final Order order, @NotNull final Score score, @NotBlank final String content, final ImageUrls imageUrls) {
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

        public Score(@NotNull @Min(0) @Max(10) Byte score) {
            this.score = score;
        }

        public Byte get() {
            return score;
        }
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class ImageUrls {
        private String imageUrls;

        public ImageUrls(final List<String> urls) {
            if (CollectionUtils.isEmpty(urls)) return;

            this.imageUrls = mapToString(urls);
        }

        private String mapToString(final List<String> urls) {
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
