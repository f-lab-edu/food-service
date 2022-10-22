package com.food.order.presentation;

import com.food.common.annotation.ApiFor;
import com.food.common.annotation.Authenticated;
import com.food.common.apiResult.SuccessResult;
import com.food.common.payment.business.external.PayService;
import com.food.common.user.business.external.model.RequestUser;
import com.food.common.user.enumeration.Role;
import com.food.order.presentation.dto.request.PayViewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiFor(roles = Role.CUSTOMER)
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@RestController
public class PayController {
    private final PayService payService;

    @PostMapping
    public ResponseEntity<SuccessResult> pay(@RequestBody @Valid PayViewRequest request,
                                             @Authenticated RequestUser requestUser) {
        payService.pay(request.toPayRequest(requestUser));

        return ResponseEntity.ok(SuccessResult.createResult());
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<SuccessResult> cancelPayment(@PathVariable @NotNull Long paymentId,
                                             @Authenticated RequestUser requestUser) {
        payService.cancelPayment(paymentId, requestUser);

        return ResponseEntity.ok(SuccessResult.createResult());
    }

    /**
     * TODO
     * Order 조리 사직인지 확인 (요청중일 때만 취소가능)
     * Order에 상태 변경 (취소)
     * Payment 상태 변경 (v)
     * 사용 Point 취소 (v)
     * 적립 Point 회수 (v)
     */


}
