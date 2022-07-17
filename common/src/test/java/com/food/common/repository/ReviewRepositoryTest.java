package com.food.common.repository;

import com.food.common.order.domain.Order;
import com.food.common.order.repository.OrderRepository;
import com.food.common.review.domain.Review;
import com.food.common.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewRepositoryTest extends CommonRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveReview() {
        Optional<Order> order = orderRepository.findById(1L);

        Review review = new Review(order.get(), new Review.Score((byte) 8), "review content 12345", new Review.ImageUrls(null));
        reviewRepository.save(review);

        assertThat(review).isNotNull();
    }
}
