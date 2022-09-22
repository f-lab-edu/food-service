package com.food.common.address.utils;

import com.food.common.utils.validation.ValidationFailureMessages;

public class AddressFailureMessages extends ValidationFailureMessages {
    public static final String POST_CODE_CANNOT_BE_BLANK = "우편번호" + CANNOT_BE_BLANK;
    public static final String POST_CODE_IS_OUT_OF_LENGTH_OF_STRING = "우편번호" + IS_OUT_OF_LENGTH_OF_STRING;
    public static final String SIDO_CANNOT_BE_BLANK = "시/도명" + CANNOT_BE_BLANK;
    public static final String SIDO_IS_OUT_OF_LENGTH_OF_STRING = "시/도명" + IS_OUT_OF_LENGTH_OF_STRING;
    public static final String SIGUNGU_CANNOT_BE_BLANK = "시/군/구명" + CANNOT_BE_BLANK;
    public static final String SIGUNGU_IS_OUT_OF_LENGTH_OF_STRING = "시/군/구명" + IS_OUT_OF_LENGTH_OF_STRING;
    public static final String TYPE_CANNOT_BE_NULL = "도로명/지번 유형" + CANNOT_BE_NULL;
    public static final String TYPE_ADDRESS_CANNOT_BE_BLANK = "도로명/지번 주소" + CANNOT_BE_BLANK;
    public static final String TYPE_ADDRESS_IS_OUT_OF_LENGTH_OF_STRING = "도로명/지번 주소" + IS_OUT_OF_LENGTH_OF_STRING;
    public static final String MAIN_NO_CANNOT_BE_NULL = "주소 본번" + CANNOT_BE_NULL;
    public static final String SUB_NO_CANNOT_BE_NULL = "주소 부번" + CANNOT_BE_NULL;
    public static final String REFERENCE_ADDRESS_IS_OUT_OF_LENGTH_OF_STRING = "참고 주소" + IS_OUT_OF_LENGTH_OF_STRING;
}
