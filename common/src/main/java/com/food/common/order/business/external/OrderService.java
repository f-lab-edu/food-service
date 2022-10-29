package com.food.common.order.business.external;

import com.food.common.order.business.external.model.OrderDoViewRequest;
import com.food.common.user.business.external.model.RequestUser;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {
    Long order(@NotNull OrderDoViewRequest request, @NotNull RequestUser requestUser);
}
