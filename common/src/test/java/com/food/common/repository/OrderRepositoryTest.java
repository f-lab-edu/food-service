package com.food.common.repository;

import com.food.common.order.domain.Order;
import com.food.common.order.repository.OrderRepository;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.repository.PaymentRepository;
import com.food.common.store.domain.Store;
import com.food.common.store.repository.StoreRepository;
import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderRepositoryTest extends CommonRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveOrder() {
        Optional<User> user = userRepository.findById(1L);
        Optional<Store> store = storeRepository.findById(1L);
        Optional<Payment> payment = paymentRepository.findById(1L);

        Order order = new Order(user.get(), store.get(), 25000, Order.Status.REQUESTED, null, payment.get());
        orderRepository.save(order);

        assertThat(order).isNotNull();
    }
}
