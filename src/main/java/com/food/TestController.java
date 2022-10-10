package com.food;

import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiFor(roles = Role.ALL)
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Food Service - test");
    }
}
