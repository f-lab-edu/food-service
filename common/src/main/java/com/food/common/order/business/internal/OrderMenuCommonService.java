package com.food.common.order.business.internal;

import com.food.common.order.business.internal.dto.OrderMenuDto;

import java.util.List;

public interface OrderMenuCommonService {
    List<OrderMenuDto> saveAll(Long orderId, List<OrderMenuDto> orderMenus);
}
