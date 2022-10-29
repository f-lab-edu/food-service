package com.food.order.business;

import com.food.common.menu.business.internal.MenuCommonService;
import com.food.common.menu.business.internal.dto.MenuDto;
import com.food.common.menu.business.internal.dto.MenuOptionDto;
import com.food.common.menu.business.internal.dto.MenuSelectionDto;
import com.food.common.order.business.external.OrderService;
import com.food.common.order.business.external.model.OrderDoViewRequest;
import com.food.common.order.business.internal.OrderCommonService;
import com.food.common.order.business.internal.OrderMenuCommonService;
import com.food.common.store.business.internal.StoreCommonService;
import com.food.common.store.business.internal.dto.StoreDto;
import com.food.common.user.business.external.model.RequestUser;
import com.food.order.business.model.Order;
import com.food.order.business.model.OrderMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultOrderService implements OrderService {
    private final StoreCommonService storeCommonService;
    private final MenuCommonService menuCommonService;

    private final OrderCommonService orderCommonService;
    private final OrderMenuCommonService orderMenuCommonService;


    @Override
    public Long order(OrderDoViewRequest request, RequestUser requestUser) {
        StoreDto store = storeCommonService.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다. storeId=" + request.getStoreId()));

        if (store.isClosed()) {
            throw new IllegalArgumentException(store.getName() + "은(는) 영업 종료되었습니다.");
        }

        List<MenuDto> menus = menuCommonService.findMenusInIds(request.getMenuIds());
        validateMenus(menus, request);

        List<OrderMenu> orderMenus = toOrderMenus(request, menus);
        Order order = new Order(requestUser.getUserId(), store, orderMenus, request.getComment());

        if (!order.hasGreaterTotalPriceThanMinOrderAmount()) {
            throw new IllegalArgumentException("최소주문금액보다 적은 주문금액입니다.");
        }

        Long savedOrderId = orderCommonService.save(order.toOrderDto());
        orderMenuCommonService.saveAll(savedOrderId, order.toOrderMenuDtos());
        return null;
    }

    private List<OrderMenu> toOrderMenus(OrderDoViewRequest request, List<MenuDto> menus) {
        List<OrderMenu> result = new ArrayList<>();
        for (MenuDto menu : menus) {
            OrderDoViewRequest.MenuRequest menuRequest = request.getMenuById(menu.getId());
            List<OrderDoViewRequest.MenuOptionRequest> optionRequests = menuRequest.getOptions();

            OrderMenu orderMenu = new OrderMenu(menu.getId(), menu.getAmount(), menuRequest.getCount(), toOrderMenuOptions(optionRequests, menu));
            result.add(orderMenu);
        }

        return result;
    }

    private void validateMenus(List<MenuDto> menus, OrderDoViewRequest request) {
        if (CollectionUtils.isEmpty(menus)) throw new IllegalArgumentException("주문 메뉴를 찾을 수 없습니다. ");

        if (request.getMenuIds().size() != menus.size()) {
            throw new IllegalArgumentException("존재하지 않는 주문메뉴가 포함되어 있습니다.");
        }

        boolean containMenusNotInOrderedStore = menus.stream().anyMatch(menu -> !menu.getStoreId().equals(request.getStoreId()));
        if (containMenusNotInOrderedStore) {
            throw new IllegalArgumentException("주문메뉴가 주문가게와 일치하지 않습니다.");
        }
    }

    private List<OrderMenu.OrderMenuOption> toOrderMenuOptions(List<OrderDoViewRequest.MenuOptionRequest> requests, MenuDto menuDto) {
        List<OrderMenu.OrderMenuOption> result = new ArrayList<>();
        for (OrderDoViewRequest.MenuOptionRequest request : requests) {
            MenuOptionDto menuOption = menuDto.getOptionById(request.getOptionId());

            List<OrderMenu.OrderMenuSelection> orderMenuSelections = toOrderMenuSelections(request, menuOption);
            result.add(new OrderMenu.OrderMenuOption(
                    menuOption.getId(), menuOption.getMaxSize(), menuOption.getMinSize(), orderMenuSelections));
        }

        return result;
    }

    private List<OrderMenu.OrderMenuSelection> toOrderMenuSelections(OrderDoViewRequest.MenuOptionRequest optionRequest, MenuOptionDto optionDto) {
        List<OrderMenu.OrderMenuSelection> result = new ArrayList<>();
        for (Long selectionId : optionRequest.getSelectionIds()) {
            MenuSelectionDto selection = optionDto.getSelection(selectionId);
            result.add(new OrderMenu.OrderMenuSelection(selectionId, selection.getAmount()));
        }

        return result;
    }
}
