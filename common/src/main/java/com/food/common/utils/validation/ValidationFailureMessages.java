package com.food.common.utils.validation;

public abstract class ValidationFailureMessages {
    protected static final String CANNOT_BE_NULL = "은(는) Null일 수 없습니다.";
    protected static final String CANNOT_BE_BLANK = "은(는) 비어있을 수 없습니다.";
    protected static final String HAS_TO_BE_POSITIVE = "은(는) 양수여야 합니다. (current value: ${validatedValue})";
    protected static final String IS_OUT_OF_LENGTH_OF_STRING = "의 길이는 {min} 이상 {max} 이하여야 합니다. (current value: ${validatedValue})";
    protected static final String HAS_TO_BE_LESS_THAN_MAX_VALUE = "의 크기는 {max} 이하여야 합니다. (current value: ${validatedValue})";
    protected static final String HAS_TO_BE_IN_EMAIL_FORMAT = "은 이메일 형식이어야합니다. (current value: ${validatedValue})";
}
