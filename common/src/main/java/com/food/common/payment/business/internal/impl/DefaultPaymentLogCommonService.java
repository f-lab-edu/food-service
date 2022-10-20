package com.food.common.payment.business.internal.impl;

import com.food.common.payment.business.external.model.payrequest.PaymentElement;
import com.food.common.payment.business.external.model.payrequest.PointPayment;
import com.food.common.payment.business.internal.PaymentLogCommonService;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.repository.PaymentLogRepository;
import com.food.common.user.business.internal.impl.PointEntityService;
import com.food.common.user.domain.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultPaymentLogCommonService implements PaymentLogCommonService {
    private final PaymentEntityService paymentEntityService;
    private final PointEntityService pointEntityService;
    private final PaymentLogRepository paymentLogRepository;

    @Override
    public void saveAll(Long paymentId, Set<PaymentElement> elements) {
        Payment payment = paymentEntityService.findById(paymentId);

        Set<PaymentLog> paymentLogs = elements.stream()
                .map(logRequest -> toEntity(payment, logRequest))
                .collect(Collectors.toSet());

        paymentLogRepository.saveAll(paymentLogs);
    }

    private PaymentLog toEntity(Payment payment, PaymentElement logRequest) {
        if (logRequest instanceof PointPayment pointPayment) {
            pointPayment.validate();
            Point point = pointEntityService.findById(pointPayment.getPointId());

            return PaymentLog.create(payment, pointPayment.method(), pointPayment.getAmount(), point);
        }

        return PaymentLog.create(payment, logRequest.method(), logRequest.getAmount());
    }
}
