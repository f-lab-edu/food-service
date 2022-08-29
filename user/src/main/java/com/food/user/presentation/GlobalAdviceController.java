package com.food.user.presentation;

import com.food.common.apiResult.FailResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalAdviceController {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<FailResult> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(FailResult.createResult(HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
    }
}
