package com.food.order.business;

import com.food.common.order.business.external.common.OrderCommonService;
import com.food.common.order.business.external.common.dto.OrderDto;
import com.food.common.payment.business.external.model.PayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultPayService {
    private final OrderCommonService orderCommonService;

    public void pay(PayRequest payment) {
        OrderDto order = orderCommonService.findById(payment.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문내역이 존재하지 않습니다. orderId=" + payment.getOrderId()));

        if (payment.hasDifferentTotalAmountAs(order)) {
            throw new IllegalArgumentException(
                    String.format("주문금액과 결제금액이 일치하지 않습니다. 주문금액: %d, 결제금액: %d", order.getAmount(), payment.getTotalAmount()));
        }

        if (payment.isUsedPoints()) {
            //TODO 포인트 사용
        }



    }
}
