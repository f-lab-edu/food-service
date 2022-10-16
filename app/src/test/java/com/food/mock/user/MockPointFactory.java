package com.food.mock.user;

import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.util.Optional;

@TestComponent
public class MockPointFactory {

    @Autowired
    private PointRepository pointRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Point point(User user, Integer changedAmount) {
        Point point = MockPoint.builder()
                .user(user)
                .changedAmount(changedAmount)
                .currentAmount(currentAmount(user))
                .payment(null)
                .build();

        return pointRepository.save(point);
    }

    private int currentAmount(User user) {
        Optional<Point> optionalPoint =
                pointRepository.findFirstByUserOrderByCreatedDateDesc(user);

        return optionalPoint.isPresent() ? optionalPoint.get().getCurrentAmount() : 0;
    }
}
