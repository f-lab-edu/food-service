package com.food.common.payment.business.internal.impl;

import com.food.common.payment.domain.Payment;
import com.food.common.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentEntityService {
    private final PaymentRepository paymentRepository;

    public Payment findById(@NotNull Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("payment를 찾을 수 없습니다. paymentId=" + id));
    }
}
