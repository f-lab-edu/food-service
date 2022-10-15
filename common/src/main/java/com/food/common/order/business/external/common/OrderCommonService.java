package com.food.common.order.business.external.common;

import com.food.common.order.business.external.common.dto.OrderDto;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderCommonService {
    Optional<OrderDto> findById(@NotNull Long id);
}
