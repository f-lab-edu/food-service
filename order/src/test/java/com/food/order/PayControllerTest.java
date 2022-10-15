package com.food.order;

import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.order.presentation.PayController;
import com.food.order.presentation.dto.request.PayViewRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PayControllerTest {

    @InjectMocks
    private PayController payController;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(payController).build();
    }


    /**
     * Controller에서 하는 일
     * 요청, 응답에 대한 스펙, 유효성 검증
     */

    /**
     * 1. 결제 완료
     * 2. 결제 취소
     */

    @DisplayName("결제 완료 요청시 성공한다")
    @Test
    public void paySuccess() throws Exception {
//        doReturn("Success").when(payService).pay();

        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(payRequest())))
                .andExpect(status().isOk())
        ;
    }

    public void cancelPaymentSuccess() {

    }

    private PayViewRequest payRequest() {
        return new PayViewRequest(1L, PaymentActionType.PAYMENT,
                Set.of(new PayViewRequest.PaymentElement(PaymentMethod.CARD, 10000)));
    }
}
