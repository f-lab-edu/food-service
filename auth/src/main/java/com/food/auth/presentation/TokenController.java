package com.food.auth.presentation;

import com.food.auth.business.TokenIssueService;
import com.food.auth.presentation.dto.TokenIssueResponse;
import com.food.common.annotation.ApiFor;
import com.food.common.annotation.Authenticated;
import com.food.common.user.dto.RequestUser;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenIssueService tokenIssueService;

    @ApiFor(roles = {Role.CUSTOMER, Role.STORE_OWNER, Role.MANAGER})
    @PostMapping("/auth/token")
    public ResponseEntity<TokenIssueResponse> createAccessToken(@Authenticated RequestUser requestUser) {
        return ResponseEntity.ok(tokenIssueService.issue(requestUser));
    }
}
