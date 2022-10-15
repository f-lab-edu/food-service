package com.food.order.presentation;

import com.food.common.annotation.ApiFor;
import com.food.common.user.enumeration.Role;
import com.food.order.presentation.dto.request.PayViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@ApiFor(roles = Role.CUSTOMER)
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@RestController
public class PayController {

    @PostMapping
    public ResponseEntity<String> pay(@RequestBody @Valid PayViewRequest request) {



        return ResponseEntity.ok("Success");
    }
}
