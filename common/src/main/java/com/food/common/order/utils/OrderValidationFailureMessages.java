package com.food.common.order.utils;

import com.food.common.utils.validation.ValidationFailureMessages;

public abstract class OrderValidationFailureMessages extends ValidationFailureMessages {
    public static class Order {
        public static final String CUSTOMER_CANNOT_BE_NULL = "구매자" + CANNOT_BE_NULL;
        public static final String STORE_CANNOT_BE_NULL = "가게" + CANNOT_BE_NULL;
        public static final String AMOUNT_CANNOT_BE_NULL = "주문 금액" + CANNOT_BE_NULL;
        public static final String AMOUNT_HAS_TO_BE_POSITIVE = "주문 금액" + HAS_TO_BE_POSITIVE;
        public static final String STATUS_CANNOT_BE_NULL = "주문 상태" + CANNOT_BE_NULL;
        public static final String COMMENT_HAS_TO_BE_BETWEEN_LENGTH = "추가 요청 사항" + IS_OUT_OF_LENGTH_OF_STRING;
    }

    public static class OrderMenu {
        public static final String ORDER_CANNOT_BE_NULL = "주문" + CANNOT_BE_NULL;
        public static final String MENU_CANNOT_BE_NULL = "메뉴" + CANNOT_BE_NULL;
        public static final String AMOUNT_CANNOT_BE_NULL = "금액" + CANNOT_BE_NULL;
        public static final String AMOUNT_HAS_TO_BE_POSITIVE = "금액" + HAS_TO_BE_POSITIVE;
        public static final String COUNT_CANNOT_BE_NULL = "수량" + CANNOT_BE_NULL;
        public static final String COUNT_HAS_TO_BE_LESS_THAN_MAX_VALUE = "수량" + HAS_TO_BE_LESS_THAN_MAX_VALUE;
    }
}
