package com.food.common.apiResult;

public abstract class ApiResult {
    private boolean success;

    protected void success() {
        success =true;
    }

    protected void fail() {
        success =false;
    }
}
