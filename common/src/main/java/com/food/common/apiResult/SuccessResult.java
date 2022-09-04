package com.food.common.apiResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class SuccessResult extends ApiResult {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object content;

    public static SuccessResult createResult() {
        SuccessResult result = new SuccessResult();
        result.success();

        return result;
    }

    public static SuccessResult createResult(Object content) {
        SuccessResult result = new SuccessResult();
        result.success();
        result.content = content;

        return result;
    }
}
