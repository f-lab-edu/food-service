package com.food.common.order.business.internal;

import com.food.common.order.business.internal.dto.OrderDto;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderCommonService {
    Optional<OrderDto> findById(@NotNull Long id);
}
