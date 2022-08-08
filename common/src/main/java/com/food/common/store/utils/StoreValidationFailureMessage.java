package com.food.common.store.utils;

import com.food.common.common.utils.ValidationFailureMessages;

public abstract class StoreValidationFailureMessage extends ValidationFailureMessages {
    public static abstract class Store {
        public static final String NOT_BLANK_STORE_NAME = "상호명" + CANNOT_BE_BLANK;
        public static final String NOT_NULL_OWNER = "사장님 정보" + CANNOT_BE_NULL;
        public static final String NOT_NULL_MIN_ORDER_AMOUNT = "최소주문금액" + CANNOT_BE_NULL;
        public static final String HAS_TO_BE_POSITIVE_MIN_ORDER_AMOUNT = "최수주문금액" + HAS_TO_BE_POSITIVE;
        public static final String NOT_NULL_STATUS = "운영 상태" + CANNOT_BE_NULL;
    }

    public static abstract class StoreOwner {
        public static final String NOT_NULL_USER = "유저 정보" + CANNOT_BE_NULL;
    }

    public static abstract class StoreOperatingTime {
        public static final String NOT_NULL_STORE = "가게 정보" + CANNOT_BE_NULL;
        public static final String NOT_NULL_WEEK = "영업 요일" + CANNOT_BE_NULL;
        public static final String NOT_NULL_OPENING_TIME = "영업 시작일시" + CANNOT_BE_NULL;
        public static final String NOT_NULL_CLOSING_TIME = "영업 종료일시" + CANNOT_BE_NULL;
    }

    public static abstract class StoreAddress {
        public static final String NOT_NULL_STORE = "가게 정보" + CANNOT_BE_NULL;
        public static final String NOT_NULL_ADDRESS = "가게 소재지" + CANNOT_BE_NULL;
        public static final String BETWEEN_LENGTH_OF_ADDRESS_DETAIL = "상세주소" + HAS_TO_BE_BETWEEN_LENGTH_OF_STRING;
    }

    public static abstract class StoreFoodCategory {
        public static final String NOT_NULL_STORE = "가게" + CANNOT_BE_NULL;
        public static final String NOT_NULL_FOOD_CATEGORY = "음식 카테고리" + CANNOT_BE_NULL;
    }
}
