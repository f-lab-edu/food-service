package com.food.common.unit.user.domain;

import com.food.common.mock.user.MockPoint;
import com.food.common.mock.user.MockUser;
import com.food.common.unit.SuperValidationTests;
import com.food.common.user.domain.Point;
import com.food.common.user.enumeration.PointType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.food.common.user.UserValidationFailureMessages.Point.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PointTest extends SuperValidationTests<Point> {
    @Test
    void validateUserInPoint() {
        Point mockPointWithNullUser = MockPoint.builder()
                .user(null)
                .build();

        Point mockPointWithNormalUser = MockPoint.builder()
                .user(MockUser.builder().build())
                .build();

        assertAll(
                () -> assertThat(failureMessagesOf(mockPointWithNullUser)).containsExactlyInAnyOrder(USER_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPointWithNormalUser)).isEmpty()
        );
    }

    @Test
    void validateTypeInPoint() {
        Point mockPointWithNullType = MockPoint.builder()
                .type(null)
                .build();

        Set<Point> mockPointsWithEnumTypes = Arrays.stream(PointType.values()).map(
                        type -> MockPoint.builder()
                                .type(type)
                                .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPointWithNullType)).containsExactlyInAnyOrder(TYPE_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPointsWithEnumTypes)).isEmpty()
        );
    }

    @Test
    void validateChangedAmountInPoint() {
        Point mockPointWithNullChangedAmount = MockPoint.builder()
                .changedAmount(null)
                .build();

        int negativeChangedAmount = -1000;
        Point mockPointWithNegativeChangedAmount = MockPoint.builder()
                .changedAmount(negativeChangedAmount)
                .build();

        Set<Point> mockPointsWithPositiveAmounts = Stream.of(0, 1000, 10000).map(amount -> MockPoint.builder()
                        .changedAmount(amount)
                        .build())
                .collect(Collectors.toSet());

        assertAll(
                () -> assertThat(failureMessagesOf(mockPointWithNullChangedAmount)).containsExactlyInAnyOrder(CHANGED_AMOUNT_CANNOT_BE_NULL),
                () -> assertThat(failureMessagesOf(mockPointWithNegativeChangedAmount)).containsExactlyInAnyOrder(formatPositive(CHANGED_AMOUNT_HAS_TO_BE_POSITIVE, negativeChangedAmount)),
                () -> assertThat(failureMessagesOf(mockPointsWithPositiveAmounts)).isEmpty()
        );
    }
}
