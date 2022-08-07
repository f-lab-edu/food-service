package com.food.common.order.repository;

import com.food.common.order.domain.OrderMenuSelection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuSelectionRepository extends JpaRepository<OrderMenuSelection, Long> {
}
