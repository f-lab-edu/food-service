package com.food.auth.presentation;

import com.food.auth.business.LoginService;
import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.auth.presentation.dto.TokenRenewResponse;
import com.food.common.annotation.ApiFor;
import com.food.common.annotation.Authenticated;
import com.food.common.apiResult.SuccessResult;
import com.food.common.user.dto.RequestUser;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class TokenController {
    private final LoginService loginService;

    @ApiFor(roles = Role.ALL)
    @PostMapping("/login")
    public ResponseEntity<SuccessResult<TokenIssueResponse>> createToken(@Valid @RequestBody LoginRequest request) {
        SuccessResult<TokenIssueResponse> result = SuccessResult.createResult(loginService.login(request));
        return ResponseEntity.ok(result);
    }

    @ApiFor(roles = Role.ALL)
    @PutMapping("/renew")
    public ResponseEntity<SuccessResult<TokenRenewResponse>> renewToken(@RequestHeader String authorization) {
        SuccessResult<TokenRenewResponse> result = SuccessResult.createResult(loginService.renew(authorization));
        return ResponseEntity.ok(result);
    }

    @ApiFor(roles = {Role.CUSTOMER, Role.STORE_OWNER, Role.MANAGER})
    @PostMapping("/logout")
    public ResponseEntity<SuccessResult<Void>> logout(@Authenticated RequestUser requestUser) {
        loginService.logout(requestUser);
        return ResponseEntity.ok(SuccessResult.createResult());
    }

}
