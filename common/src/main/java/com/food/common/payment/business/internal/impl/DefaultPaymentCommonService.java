package com.food.common.payment.business.internal.impl;

import com.food.common.order.business.internal.impl.OrderEntityService;
import com.food.common.order.domain.Order;
import com.food.common.payment.business.internal.PaymentCommonService;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPaymentCommonService implements PaymentCommonService {
    private final PaymentRepository paymentRepository;
    private final OrderEntityService orderEntityService;

    @Override
    public Long save(Long orderId, PaymentActionType actionType) {
        Order order = orderEntityService.findById(orderId);
        Payment payment = Payment.create(order, actionType);

        return paymentRepository.save(payment).getId();
    }
}
