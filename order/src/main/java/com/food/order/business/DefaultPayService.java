package com.food.order.business;

import com.food.common.order.business.internal.OrderCommonService;
import com.food.common.order.business.internal.dto.OrderDto;
import com.food.common.payment.business.external.PayService;
import com.food.common.payment.business.external.model.PayRequest;
import com.food.common.payment.business.internal.PaymentCommonService;
import com.food.common.payment.business.internal.PaymentLogCommonService;
import com.food.common.user.business.external.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPayService implements PayService {
    private final OrderCommonService orderCommonService;
    private final PointService pointService;
    private final PaymentCommonService paymentCommonService;
    private final PaymentLogCommonService paymentLogCommonService;

    public void pay(PayRequest payment) {
        OrderDto order = orderCommonService.findById(payment.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("주문내역이 존재하지 않습니다. orderId=" + payment.getOrderId()));

        if (payment.hasDifferentTotalAmountAs(order)) {
            throw new IllegalArgumentException(
                    String.format("주문금액과 결제금액이 일치하지 않습니다. 주문금액: %d, 결제금액: %d", order.getAmount(), payment.getTotalAmount()));
        }

        usePoints(payment);

        Long paymentId = paymentCommonService.save(payment.toPaymentSaveDto());
        paymentLogCommonService.saveAll(payment.toPaymentLogSaveDto(paymentId));
    }

    private void usePoints(PayRequest payment) {
        Optional<PayRequest.PaymentPoint> findPointPayment = payment.findPointPayment();
        if(findPointPayment.isPresent()) {
            PayRequest.PaymentPoint paymentPoint = findPointPayment.get();
            Long usedPointId = pointService.use(paymentPoint.toPointsUseRequest());
            paymentPoint.updateUsedPointId(usedPointId);
        }
    }
}
