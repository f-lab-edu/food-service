package com.food.common.foodCategory.utils;

import com.food.common.common.utils.ValidationFailureMessages;

public class FoodCategoryFailureMessages extends ValidationFailureMessages {
    public static final String NOT_BLANK_NAME = "음식 카테고리명" + CANNOT_BE_BLANK;
    public static final String BETWEEN_LENGTH_OF_NAME = "음식 카테고리명" + HAS_TO_BE_BETWEEN_LENGTH_OF_STRING;
}
