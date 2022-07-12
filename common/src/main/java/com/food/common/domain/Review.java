package com.food.common.domain;

import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;

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

    @Embedded
    private Score score;

    private String content;

    private String ImageUrls;

    public Review(User user, Store store, Score score, String content, String imageUrls) {
        this.userId = user.getUserId();
        this.storeId = store.getId();
        this.score = score;
        this.content = content;
        ImageUrls = imageUrls;
    }

    @NoArgsConstructor(access = PROTECTED)
    @Embeddable
    public static class Score {
        private Byte value;

        public Score(Byte value) {
            validate(value);
            this.value = value;
        }

        private void validate(Byte amount) {
            if (0 <= amount && amount <= 10) return;

            throw new IllegalArgumentException();
        }

        public Byte get() {
            return value;
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
            StringBuilder result = new StringBuilder();
            urls.forEach(result::append);

            return result.toString();
        }
    }
}
