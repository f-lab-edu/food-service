package com.food.common.repository;

import com.food.common.payment.domain.Payment;
import com.food.common.payment.repository.PaymentRepository;
import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentRepositoryTest extends CommonRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void shouldSavePayment() {
        Optional<User> user = userRepository.findById(1L);
        Payment payment = new Payment(25000, Payment.Method.CARD, Payment.Status.PAYMENT_REQUESTED, Payment.PaymentPoints.notUse(user.get()));

        paymentRepository.save(payment);

        assertThat(payment).isNotNull();
    }
}
