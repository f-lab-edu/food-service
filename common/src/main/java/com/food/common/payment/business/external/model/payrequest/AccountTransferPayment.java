package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.enumeration.PaymentMethod;

public final class AccountTransferPayment extends PaymentElement {
    public AccountTransferPayment(Integer amount) {
        super(PaymentMethod.ACCOUNT_TRANSFER, amount);
    }
}
