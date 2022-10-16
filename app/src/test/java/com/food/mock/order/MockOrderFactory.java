package com.food.mock.order;

import com.food.common.order.domain.Order;
import com.food.common.order.enumeration.OrderStatus;
import com.food.common.order.repository.OrderRepository;
import com.food.common.store.domain.Store;
import com.food.common.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockOrderFactory {

    @Autowired
    private OrderRepository orderRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Order order(User customer, Store store) {
        Order order = MockOrder.builder()
                .customer(customer)
                .store(store)
                .build();

        return orderRepository.save(order);
    }

    public Order order(User customer, Store store, Integer amount, OrderStatus status) {
        Order order = MockOrder.builder()
                .customer(customer)
                .store(store)
                .amount(amount)
                .status(status)
                .build();

        return orderRepository.save(order);
    }
}
