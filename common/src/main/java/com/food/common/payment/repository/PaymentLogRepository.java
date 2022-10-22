package com.food.common.payment.repository;

import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
    List<PaymentLog> findAllByPayment(Payment payment);
}
