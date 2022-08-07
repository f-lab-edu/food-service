package com.food.common.order.repository;

import com.food.common.order.domain.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
