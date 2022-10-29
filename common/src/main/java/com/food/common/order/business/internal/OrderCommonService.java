package com.food.common.order.business.internal;

import com.food.common.order.business.internal.dto.OrderDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@Validated
public interface OrderCommonService {
    Optional<OrderDto> findById(@NotNull @Positive Long id);

    Long save(OrderDto order);
}
