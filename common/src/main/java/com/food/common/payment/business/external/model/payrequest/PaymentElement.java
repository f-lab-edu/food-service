package com.food.common.payment.business.external.model.payrequest;

import com.food.common.payment.enumeration.PaymentMethod;

import javax.validation.constraints.NotNull;

public abstract class PaymentElement {
    @NotNull
    protected final Integer amount;

    protected PaymentElement(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public abstract PaymentMethod method();
}
