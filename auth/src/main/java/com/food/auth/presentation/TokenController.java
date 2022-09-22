package com.food.auth.presentation;

import com.food.auth.business.DefaultLoginService;
import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.common.annotation.ApiFor;
import com.food.common.apiResult.SuccessResult;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class TokenController {
    private final DefaultLoginService defaultLoginService;

    @ApiFor(roles = Role.ALL)
    @PostMapping("/login")
    public ResponseEntity<SuccessResult<TokenIssueResponse>> createToken(@Valid @RequestBody LoginRequest request) {
        SuccessResult<TokenIssueResponse> result = SuccessResult.createResult(defaultLoginService.login(request));
        return ResponseEntity.ok(result);
    }

    @ApiFor(roles = Role.ALL)
    @PutMapping("/renew")
    public ResponseEntity<TokenIssueResponse> renewToken(@RequestHeader String authorization) {
        return ResponseEntity.ok(defaultLoginService.renew(authorization));
    }

}
