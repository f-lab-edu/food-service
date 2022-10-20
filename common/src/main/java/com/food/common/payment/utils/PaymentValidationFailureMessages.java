package com.food.common.payment.utils;

import com.food.common.utils.validation.ValidationFailureMessages;

public abstract class PaymentValidationFailureMessages extends ValidationFailureMessages {
    public static abstract class Payment {
        public static final String ORDER_CANNOT_BE_NULL = "주문" + CANNOT_BE_NULL;
        public static final String TYPE_CANNOT_BE_NULL = "결제 타입" + CANNOT_BE_NULL;
    }

    public static abstract class PaymentLog {
        public static final String PAYMENT_CANNOT_BE_NULL = "결제" + CANNOT_BE_NULL;
        public static final String METHOD_CANNOT_BE_NULL = "결제 수단" + CANNOT_BE_NULL;
        public static final String AMOUNT_HAS_TO_BE_POSITIVE = "결제 금액" + HAS_TO_BE_POSITIVE;
        public static final String AMOUNT_CANNOT_BE_NULL = "결제 금액" + CANNOT_BE_NULL;
    }
}
