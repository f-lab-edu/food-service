package com.food.user.presentation;

import com.food.common.apiResult.ApiResult;
import com.food.common.apiResult.SuccessResult;
import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import com.food.user.service.DefaultAccountFindService;
import com.food.common.user.business.dto.response.accountFind.AccountFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@ApiFor(roles = Role.ALL)
@RequestMapping("/users")
@RestController
public class AccountController {
    private final DefaultAccountFindService userFindService;

    @GetMapping("/{userId}/account")
    public ResponseEntity<ApiResult> findAccountByUserId(@PathVariable Long userId) {
        AccountFindResponse response = userFindService.findAccountByUserId(userId);
        return ResponseEntity.ok(SuccessResult.createResult(response));
    }

    @ApiFor(roles = Role.STORE_OWNER)
    @GetMapping("/manage")
    public ResponseEntity<Void> temp() {
        return ResponseEntity.noContent().build();
    }
}
