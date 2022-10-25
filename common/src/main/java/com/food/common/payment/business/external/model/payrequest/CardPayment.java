package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.enumeration.PaymentMethod;

public final class CardPayment extends PaymentElement {
    public CardPayment(Integer amount) {
        super(amount);
    }

    @Override
    public PaymentMethod method() {
        return PaymentMethod.CARD;
    }
}
