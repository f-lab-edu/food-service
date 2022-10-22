package com.food.common.payment.business.internal.impl;

import com.food.common.payment.business.internal.PaymentLogCommonService;
import com.food.common.payment.business.internal.model.PaymentLogsSaveDto;
import com.food.common.payment.domain.Payment;
import com.food.common.payment.domain.PaymentLog;
import com.food.common.payment.repository.PaymentLogRepository;
import com.food.common.user.business.internal.impl.PointEntityService;
import com.food.common.user.domain.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void saveAll(PaymentLogsSaveDto request) {
        Payment payment = paymentEntityService.findById(request.getPaymentId());

        Set<PaymentLog> paymentLogs = request.getLogs().stream()
                .map(logRequest -> toEntity(payment, logRequest))
                .collect(Collectors.toSet());

        paymentLogRepository.saveAll(paymentLogs);
    }

    private PaymentLog toEntity(Payment payment, PaymentLogsSaveDto.PaymentLog logRequest) {
        if (logRequest.isPoint()) {
            Point point = pointEntityService.findById(logRequest.getPointId());
            return PaymentLog.create(payment, logRequest.getMethod(), logRequest.getAmount(), point);
        }

        return PaymentLog.create(payment, logRequest.getMethod(), logRequest.getAmount());
    }

    @Override
    public List<PaymentLog> findAllByPaymentId(Long paymentId) {
        Payment payment = paymentEntityService.findById(paymentId);
        return paymentLogRepository.findAllByPayment(payment);
    }
}
