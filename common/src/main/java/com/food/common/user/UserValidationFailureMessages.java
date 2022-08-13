package com.food.common.user;

import com.food.common.common.utils.ValidationFailureMessages;

public abstract class UserValidationFailureMessages extends ValidationFailureMessages {
    public static abstract class User {
        public static final String NICKNAME_CANNOT_BLANK = "닉네임" + CANNOT_BE_BLANK;
        public static final String NICKNAME_HAS_TO_BE_BETWEEN_LENGTH = "닉네임" + IS_OUT_OF_LENGTH_OF_STRING;
    }

    public static abstract class AppAccount {
        public static final String LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING = "로그인 아이디" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String LOGIN_ID_HAS_TO_BE_IN_EMAIL_FORMAT = "로그인 아이디" + HAS_TO_BE_IN_EMAIL_FORMAT;
        public static final String PASSWORD_CANNOT_BE_BLANK = "비밀번호" + CANNOT_BE_BLANK;
        public static final String PASSWORD_HAS_TO_BE_BETWEEN_LENGTH = "비밀번호" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
    }

    public static abstract class SocialAccount {
        public static final String LOGIN_ID_CANNOT_BE_BLANK = "로그인 아이디" + CANNOT_BE_BLANK;
        public static final String LOGIN_ID_IS_OUT_OF_LENGTH_OF_STRING = "로그인 아이디" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
    }

    public static abstract class UserAddress {
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
        public static final String NAME_HAS_TO_BE_BETWEEN_LENGTH = "주소별칭" + IS_OUT_OF_LENGTH_OF_STRING;
        public static final String ADDRESS_CANNOT_BE_NULL = "주소" + CANNOT_BE_NULL;
        public static final String ADDRESS_DETAIL_HAS_TO_BE_BETWEEN_LENGTH = "직접 입력 상세 주소" + IS_OUT_OF_LENGTH_OF_STRING;
    }

    public static abstract class Point {
        public static final String USER_CANNOT_BE_NULL = "유저" + CANNOT_BE_NULL;
        public static final String TYPE_CANNOT_BE_NULL = "적립/사용 타입" + CANNOT_BE_NULL;
        public static final String CHANGED_AMOUNT_HAS_TO_BE_POSITIVE = "포인트 금액" + HAS_TO_BE_POSITIVE;
        public static final String CHANGED_AMOUNT_CANNOT_BE_NULL = "포인트 금액" + CANNOT_BE_NULL;
        public static final String CURRENT_AMOUNT_HAS_TO_BE_POSITIVE = "잔여 포인트 금액" + HAS_TO_BE_POSITIVE;
        public static final String CURRENT_AMOUNT_CANNOT_BE_NULL = "잔여 포인트 금액" + CANNOT_BE_NULL;
        public static final String PAYMENT_CANNOT_BE_NULL = "결제정보" + CANNOT_BE_NULL;
    }
}
