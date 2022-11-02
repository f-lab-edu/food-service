package com.food.api.order;

import com.food.SuperIntegrationTest;
import com.food.common.menu.domain.Menu;
import com.food.common.menu.domain.MenuOption;
import com.food.common.menu.domain.MenuSelection;
import com.food.common.menu.repository.MenuQueryRepositoryImpl;
import com.food.common.order.business.external.model.OrderDoViewRequest;
import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreOwner;
import com.food.common.store.enumeration.StoreOpenStatus;
import com.food.common.user.domain.User;
import com.food.mock.menu.MockMenu;
import com.food.mock.menu.MockMenuOption;
import com.food.mock.menu.MockMenuSelection;
import com.food.mock.store.MockStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderApiTests extends SuperIntegrationTest {

    private final String DOCUMENT_AUTH = "payment/do-order/";

    private StoreOwner mockStoreOwner;

    @Autowired
    private MenuQueryRepositoryImpl menuQueryRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @BeforeEach
    void setup() {
        User storeOwnerUser = userFactory.user("i'm a storeowner");
        mockStoreOwner = storeOwnerFactory.storeOwner(storeOwnerUser);
    }

    @Test
    void shouldBeSuccessResult_whenRequestOrder() throws Exception {
        //given
        //운영중인 가게를 등록한다.
        Store mockStore = storeFactory.store(MockStore.builder()
                .owner(mockStoreOwner)
                .status(StoreOpenStatus.OPEN)
                .minOrderAmount(15000)
                .name("떡볶이 참 잘하는 집")
                .build());

        //2가지 메뉴를 등록한다.
        Menu mockMenu1 = menuFactory.menu(MockMenu.builder()
                .store(mockStore)
                .amount(12000)
                .cookingTime(20)
                .name("바질 떡볶이")
                .build());

        MenuOption mockOptionOfMenu1 = menuOptionFactory.menuOption(MockMenuOption.builder()
                .menu(mockMenu1)
                .name("매운맛 선택")
                .minSize((byte) 1)
                .maxSize((byte) 1)
                .build());

        List<MenuSelection> mockMenuSelections = menuSelectionFactory.menuSelections(
                MockMenuSelection.builder()
                        .option(mockOptionOfMenu1)
                        .selection("순한맛")
                        .amount(0)
                        .build(),
                MockMenuSelection.builder()
                        .option(mockOptionOfMenu1)
                        .selection("중간맛")
                        .amount(0)
                        .build(),
                MockMenuSelection.builder()
                        .option(mockOptionOfMenu1)
                        .selection("매운맛")
                        .amount(0)
                        .build());


        Menu mockMenu2 = menuFactory.menu(MockMenu.builder()
                .store(mockStore)
                .amount(3000)
                .cookingTime(10)
                .name("주먹밥")
                .build());

        //주문 요청 Request
        OrderDoViewRequest.MenuOptionRequest menuOptionRequest = new OrderDoViewRequest.MenuOptionRequest(mockOptionOfMenu1.getId(), List.of(mockMenuSelections.get(1).getOptionId()));
        OrderDoViewRequest.MenuRequest menuRequest1 = new OrderDoViewRequest.MenuRequest(mockMenu1.getId(), (byte) 1, List.of(menuOptionRequest));
        OrderDoViewRequest.MenuRequest menuRequest2 = new OrderDoViewRequest.MenuRequest(mockMenu2.getId(), (byte) 1, null);

        OrderDoViewRequest request = new OrderDoViewRequest(mockStore.getId(), List.of(menuRequest1, menuRequest2), "맛있게 해주세요.");

        //when & then
        mvc.perform(post("/api/orders")
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(AUTHORIZATION, createAuthentication())
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(true));
    }
}
