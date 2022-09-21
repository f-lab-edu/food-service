package com.food.auth.presentation;

import com.food.auth.business.DefaultTokenIssueService;
import com.food.auth.presentation.dto.LoginRequest;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class TokenController {
    private final DefaultTokenIssueService defaultTokenIssueService;

    @ApiFor(roles = Role.ALL)
    @PostMapping("/login")
    public ResponseEntity<TokenIssueResponse> createToken(LoginRequest request) {
        return ResponseEntity.ok(defaultTokenIssueService.login(request));
    }

    @ApiFor(roles = Role.ALL)
    @PutMapping("/renew")
    public ResponseEntity<TokenIssueResponse> renewToken(@RequestHeader String authorization) {
        return ResponseEntity.ok(defaultTokenIssueService.renew(authorization));
    }

}
