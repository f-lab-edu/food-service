package com.food.common.common.utils;

public abstract class ValidationFailureMessages {
    protected static final String CANNOT_BE_NULL = "은(는) Null일 수 없습니다.";
    protected static final String CANNOT_BE_BLANK = "은(는) 비어있을 수 없습니다.";
    protected static final String HAS_TO_BE_POSITIVE = "은(는) 양수여야 합니다. (current value: ${validatedValue})";
    protected static final String HAS_TO_BE_BETWEEN_LENGTH_OF_STRING = "의 길이는 {min} 이상 {max} 이하여야 합니다. (current value: ${validatedValue})";
}
