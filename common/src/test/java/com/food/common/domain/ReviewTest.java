package com.food.common.domain;

import com.food.common.review.domain.Review;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReviewTest {

    @ParameterizedTest
    @ValueSource(bytes = {-1, 11})
    void shouldBeThrownExceptionWhenOutOfScope(byte value) {
        assertThatThrownBy(() -> new Review.Score(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void shouldCreateReviewScore(byte value) {
        assertThat(new Review.Score(value))
                .isNotNull();

    }
}
