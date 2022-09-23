package com.food.api;

import com.food.common.annotation.ApiFor;
import com.food.common.apiResult.FailResult;
import com.food.common.apiResult.SuccessResult;
import com.food.common.user.enumeration.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiFor(roles = Role.ALL)
@RestController
public class RestDocsIndexController {

    /**************************************************************************************************************
     * 성공 결과 케이스 가져오기
     *************************************************************************************************************/
    @GetMapping("/api/result/success")
    public ResponseEntity<SuccessResult<Object>> getSuccessResult() {
        return ResponseEntity.ok(SuccessResult.createResult(new Object()));
    }

    /**************************************************************************************************************
     * 실패 결과 케이스 가져오기
     *************************************************************************************************************/
    @GetMapping("/api/result/failure")
    public ResponseEntity<FailResult> getFailResult() {
        return ResponseEntity.ok(FailResult.createResult("test-12345", "failure message"));
    }
}
