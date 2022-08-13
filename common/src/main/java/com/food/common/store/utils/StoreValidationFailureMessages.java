package com.food.common.store.utils;

import com.food.common.common.utils.ValidationFailureMessages;

public abstract class StoreValidationFailureMessages extends ValidationFailureMessages {
    public static abstract class Store {
        public static final String STORE_NAME_CANNOT_BE_BLANK = "상호명" + CANNOT_BE_BLANK;
        public static final String STORE_OWNER_CANNOT_BE_NULL = "사장님 정보" + CANNOT_BE_NULL;
        public static final String MIN_ORDER_AMOUNT_CANNOT_BE_NULL = "최소주문금액" + CANNOT_BE_NULL;
        public static final String MIN_ORDER_AMOUNT_HAS_TO_BE_POSITIVE = "최수주문금액" + HAS_TO_BE_POSITIVE;
        public static final String OPEN_STATUS_CANNOT_BE_NULL = "운영 상태" + CANNOT_BE_NULL;
    }

    public static abstract class StoreOwner {
        public static final String USER_CANNOT_BE_NULL = "유저 정보" + CANNOT_BE_NULL;
    }

    public static abstract class StoreOperatingTime {
        public static final String STORE_CANNOT_BE_NULL = "가게 정보" + CANNOT_BE_NULL;
        public static final String WEEK_CANNOT_NULL = "영업 요일" + CANNOT_BE_NULL;
        public static final String OPENING_TIME_CANNOT_NULL = "영업 시작일시" + CANNOT_BE_NULL;
        public static final String CLOSING_TIME_CANNOT_NULL = "영업 종료일시" + CANNOT_BE_NULL;
    }

    public static abstract class StoreAddress {
        public static final String STORE_CANNOT_BE_NULL = "가게 정보" + CANNOT_BE_NULL;
        public static final String ADDRESS_CANNOT_BE_NULL = "가게 소재지" + CANNOT_BE_NULL;
        public static final String ADDRESS_DETAIL_HAS_TO_BE_BETWEEN_LENGTH = "상세주소" + IS_OUT_OF_LENGTH_OF_STRING;
    }

    public static abstract class StoreFoodCategory {
        public static final String STORE_CANNOT_BE_NULL = "가게" + CANNOT_BE_NULL;
        public static final String FOOD_CATEGORY_CANNOT_BE_NULL = "음식 카테고리" + CANNOT_BE_NULL;
    }

    public static abstract class StoreImage {
        public static final String IMAGE_CANNOT_BE_NULL = "이미지" + CANNOT_BE_NULL;
        public static final String STORE_CANNOT_BE_NULL = "가게" + CANNOT_BE_NULL;
    }
}
