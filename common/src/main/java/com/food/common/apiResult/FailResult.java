package com.food.common.apiResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class FailResult extends ApiResult {
    private String code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object content;

    public static FailResult createResult(String code, String message) {
        FailResult result = new FailResult();
        result.fail(code, message);

        return result;
    }

    protected void fail(String code, String message) {
        super.fail();
        this.code = code;
        this.message = message;
    }
}
