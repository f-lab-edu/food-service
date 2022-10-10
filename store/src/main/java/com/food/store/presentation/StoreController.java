package com.food.store.presentation;

import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiFor(roles = Role.ALL)
@RestController
public class StoreController {

    @GetMapping("/stores")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("store controller");
    }
}
