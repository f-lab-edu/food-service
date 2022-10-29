package com.food.mock.user;

import com.food.common.payment.domain.Payment;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.PointType;
import com.food.common.user.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockPointFactory {

    @Autowired
    private PointRepository pointRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Point point(User user, Integer changedAmount, Payment payment) {
        Point point = MockPoint.builder()
                .user(user)
                .changedAmount(changedAmount)
                .currentAmount(changedAmount)
                .type(PointType.COLLECT)
                .payment(payment)
                .build();

        return pointRepository.save(point);
    }
}
