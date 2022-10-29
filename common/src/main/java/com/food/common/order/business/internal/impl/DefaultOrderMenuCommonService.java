package com.food.common.order.business.internal.impl;

import com.food.common.menu.business.internal.impl.MenuEntityService;
import com.food.common.menu.domain.Menu;
import com.food.common.order.business.internal.OrderMenuCommonService;
import com.food.common.order.business.model.OrderMenuDto;
import com.food.common.order.domain.Order;
import com.food.common.order.domain.OrderMenu;
import com.food.common.order.repository.OrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultOrderMenuCommonService implements OrderMenuCommonService {
    private final OrderMenuRepository orderMenuRepository;
    private final OrderEntityService orderEntityService;
    private final MenuEntityService menuEntityService;

    @Override
    public List<OrderMenuDto> saveAll(Long orderId, List<OrderMenuDto> orderMenus) {
        Order order = orderEntityService.findById(orderId);
        Map<Long, Menu> menus = menuEntityService.findAllByIdIn(toMenuIds(orderMenus));

        List<OrderMenu> savedEntities = orderMenuRepository.saveAll(toEntities(orderMenus, order, menus));
        return savedEntities.stream()
                .map(OrderMenuDto::new)
                .collect(Collectors.toList());
    }

    private static List<OrderMenu> toEntities(List<OrderMenuDto> orderMenus, Order order, Map<Long, Menu> menus) {
        return orderMenus.stream()
                .map(orderMenu -> OrderMenu.create(order, menus.get(orderMenu.getMenuId()), orderMenu.getAmount(), orderMenu.getCount()))
                .toList();
    }

    private List<Long> toMenuIds(List<OrderMenuDto> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenuDto::getMenuId)
                .collect(Collectors.toList());
    }
}
