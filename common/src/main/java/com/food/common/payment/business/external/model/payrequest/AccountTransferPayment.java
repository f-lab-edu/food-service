package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.enumeration.PaymentMethod;

public final class AccountTransferPayment extends PaymentElement {
    public AccountTransferPayment(Integer amount) {
        super(amount);
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.ACCOUNT_TRANSFER;
    }
}
