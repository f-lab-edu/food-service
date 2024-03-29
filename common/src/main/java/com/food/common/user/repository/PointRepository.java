package com.food.common.user.repository;

import com.food.common.payment.domain.Payment;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findFirstByUserOrderByCreatedDateDesc(User user);

    List<Point> findAllByPaymentOrderByCreatedDateDesc(Payment payment);
}
