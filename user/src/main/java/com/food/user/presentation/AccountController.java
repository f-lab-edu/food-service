package com.food.user.presentation;

import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import com.food.user.business.DefaultAccountFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@ApiFor(roles = Role.ALL)
@RequestMapping("/accounts")
@RestController
public class AccountController {
    private final DefaultAccountFindService accountFindService;
}
