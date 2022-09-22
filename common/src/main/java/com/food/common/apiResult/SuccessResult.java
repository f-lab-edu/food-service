package com.food.common.apiResult;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SuccessResult<T> extends ApiResult {
    private T content;

    public static SuccessResult<Void> createResult() {
        SuccessResult<Void> result = new SuccessResult<>();
        result.success();

        return result;
    }

    public static <T> SuccessResult<T> createResult(T content) {
        SuccessResult<T> result = new SuccessResult<>();
        result.success();
        result.content = content;

        return result;
    }
}
