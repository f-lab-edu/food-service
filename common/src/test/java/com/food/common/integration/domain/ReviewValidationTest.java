package com.food.common.integration.domain;

import com.food.common.common.domain.Point;
import com.food.common.order.domain.Order;
import com.food.common.payment.domain.Payment;
import com.food.common.review.domain.Review;
import com.food.common.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Constructor;
import java.util.Set;

import static com.food.common.integration.domain.MenuValidationTest.createDummyStore;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewValidationTest extends DomainValidationIntegrationTest {
    private Set<ConstraintViolation<Review>> getViolationsOfReviewConstructor(Order order, Review.Score score, String content, Review.ImageUrls imageUrls) throws NoSuchMethodException {
        Constructor<Review> constructor = Review.class.getConstructor(Order.class, Review.Score.class, String.class, Review.ImageUrls.class);

        Object[] parameterValues = {order, score, content, imageUrls};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("Review 생성자에 Order를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullOrder_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Review>> constraintViolations =
                getViolationsOfReviewConstructor(null, createReviewScore(), "content test", null);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Review 생성자에 Score를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullScore_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Review>> constraintViolations =
                getViolationsOfReviewConstructor(createDummyOrder(), null, "content test", null);

        assertEquals(constraintViolations.size(), 1);
    }

    @Test
    @DisplayName("Review 생성자에 Content를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullContent_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Review>> constraintViolations =
                getViolationsOfReviewConstructor(createDummyOrder(), createReviewScore(), null, null);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("Review 생성자에 Content를 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithBlankContent_thenCorrectNumberOfViolations(String content) throws NoSuchMethodException {
        Set<ConstraintViolation<Review>> constraintViolations =
                getViolationsOfReviewConstructor(createDummyOrder(), createReviewScore(), content, null);

        assertEquals(constraintViolations.size(), 1);
    }

    private Set<ConstraintViolation<Review.Score>> getViolationsOfReviewScoreConstructor(Byte score) throws NoSuchMethodException {
        Constructor<Review.Score> constructor = Review.Score.class.getConstructor(Byte.class);

        Object[] parameterValues = {score};

        return executableValidator.validateConstructorParameters(constructor, parameterValues);
    }

    @Test
    @DisplayName("Review.Score 생성자에 score를 null로 넣으면 유효성 검증에 실패한다.")
    void validationWithNullReviewScore_thenCorrectNumberOfViolations() throws NoSuchMethodException {
        Set<ConstraintViolation<Review.Score>> constraintViolations = getViolationsOfReviewScoreConstructor(null);

        assertEquals(constraintViolations.size(), 1);
    }

    @ParameterizedTest
    @ValueSource(bytes = {-3, -1, 11, 15})
    @DisplayName("Review 생성자에 Content를 공백으로 넣으면 유효성 검증에 실패한다.")
    void validationWithReviewScoreOutOfRange_thenCorrectNumberOfViolations(Byte score) throws NoSuchMethodException {
        Set<ConstraintViolation<Review.Score>> constraintViolations = getViolationsOfReviewScoreConstructor(score);

        assertEquals(constraintViolations.size(), 1);
    }

    private Order createDummyOrder() {
        User dummyUser = createDummyUser();
        return new Order(dummyUser, createDummyStore(), 24000, Order.Status.REQUESTED, null, createDummyPayment(dummyUser));
    }

    private Payment createDummyPayment(User user) {
        return new Payment(24000, Payment.Method.CARD, Payment.Status.PAYMENT_COMPLETED, Payment.PaymentPoints.notUse(user));
    }

    private Review.Score createReviewScore() {
        return new Review.Score((byte) 0);
    }

    private User createDummyUser() {
        return new User("userA", "passwordABCD", "userA", new Point(1000));
    }
}
