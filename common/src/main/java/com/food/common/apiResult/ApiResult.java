package com.food.common.apiResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public abstract class ApiResult {
    private boolean success;

    protected void success() {
        success =true;
    }

    protected void fail() {
        success =false;
    }
}
