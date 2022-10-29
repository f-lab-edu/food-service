package com.food.order.presentation;

import com.food.common.annotation.ApiFor;
import com.food.common.annotation.Authenticated;
import com.food.common.apiResult.SuccessResult;
import com.food.common.order.business.external.OrderService;
import com.food.common.user.business.external.model.RequestUser;
import com.food.common.user.enumeration.Role;
import com.food.common.order.business.external.model.OrderDoViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiFor(roles = Role.CUSTOMER)
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<SuccessResult> order(@Authenticated RequestUser requestUser,
                                               @RequestBody OrderDoViewRequest request) {

        orderService.order(request, requestUser);

        return ResponseEntity.ok(SuccessResult.createResult());
    }

}
