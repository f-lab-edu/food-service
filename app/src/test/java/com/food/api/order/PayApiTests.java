package com.food.api.order;

import com.food.SuperIntegrationTest;
import com.food.common.order.domain.Order;
import com.food.common.order.enumeration.OrderStatus;
import com.food.common.order.repository.OrderRepository;
import com.food.common.payment.enumeration.PaymentActionType;
import com.food.common.payment.enumeration.PaymentMethod;
import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.Point;
import com.food.common.user.domain.User;
import com.food.common.user.repository.PointRepository;
import com.food.mock.order.MockOrder;
import com.food.mock.user.MockPoint;
import com.food.order.presentation.dto.request.PayViewRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PayApiTests extends SuperIntegrationTest {
    private final String DOCUMENT_AUTH = "payment/do-pay/";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PointRepository pointRepository;

    private Store mockStore;

    @BeforeEach
    void setup() {
        User storeOwnerUser = userFactory.user("i'm a storeowner");
        StoreOwner mockStoreOwner = storeOwnerFactory.storeOwner(storeOwnerUser);
        mockStore = storeFactory.store(mockStoreOwner);
    }

    @Test
    void shouldSavePayment() throws Exception {
        //given
        Order mockOrder = MockOrder.builder()
                .customer(mockUser)
                .store(mockStore)
                .amount(50000)
                .status(OrderStatus.REQUEST)
                .build();
        orderRepository.save(mockOrder);

        Point point = MockPoint.builder()
                .user(mockUser)
                .changedAmount(1000)
                .currentAmount(0)
                .payment(null)
                .build();
        pointRepository.save(point);

        PayViewRequest request = new PayViewRequest(mockOrder.getId(), PaymentActionType.PAYMENT,
                Set.of(new PayViewRequest.PaymentElementViewRequest(PaymentMethod.POINT, 1000),
                       new PayViewRequest.PaymentElementViewRequest(PaymentMethod.CARD, mockOrder.getAmount()-1000)
        ));

        //when & then
        mvc.perform(post("/api/payments")
                        .header(ACCEPT, APPLICATION_JSON_VALUE)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .header(AUTHORIZATION, createAuthentication())
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(true))
                .andDo(document(DOCUMENT_AUTH + "success",
                        requestHeaders(
                                headerWithName(ACCEPT).description("accept"),
                                headerWithName(CONTENT_TYPE).description("content type")
                        ),
                        requestFields(
                                fieldWithPath("orderId").type(NUMBER).description("주문 고유번호"),
                                fieldWithPath("actionType").type(STRING).description("결제 완료/취소 여부"),
                                fieldWithPath("elements[].method").type(STRING).description("결제 수단"),
                                fieldWithPath("elements[].amount").type(NUMBER).description("결제 금액")
                        ),
                        responseFields(
                                fieldWithPath("success").type(BOOLEAN).description("정상 처리 여부")
                        )
                ));
    }
}
