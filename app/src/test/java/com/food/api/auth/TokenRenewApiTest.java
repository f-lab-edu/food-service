package com.food.api.auth;

import com.food.SuperIntegrationTest;
import com.food.common.user.business.internal.RefreshTokenCommonService;
import com.food.common.user.business.internal.dto.RefreshTokenDto;
import com.food.common.user.domain.AppAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenRenewApiTest extends SuperIntegrationTest {
    private final String DOCUMENT_AUTH = "auth/renew-token/";
    private String refreshToken;

    @Autowired
    private RefreshTokenCommonService loginService;

    @BeforeEach
    protected void setup(RestDocumentationContextProvider restDocumentation) {
        super.setup(restDocumentation);

        AppAccount mockAccount = accountFactory.appAccount(mockUser);
        RefreshTokenDto refreshTokenCreated = loginService.create(mockAccount.getUserId());
        refreshToken = refreshTokenCreated.getValue();
    }

    @Test
    void shouldRenewToken() throws Exception {
        mvc.perform(put("/auth/renew")
                        .header(ACCEPT, APPLICATION_JSON_VALUE)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .header(AUTHORIZATION, refreshToken)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(true))
                .andDo(document(DOCUMENT_AUTH +"success",
                        requestHeaders(
                                headerWithName(ACCEPT).description("accept"),
                                headerWithName(CONTENT_TYPE).description("content type"),
                                headerWithName(AUTHORIZATION).description("refresh token value")
                        ),
                        responseFields(
                                fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
                                subsectionWithPath("content").description("내용")
                        ),
                        responseFields(
                                beneathPath("content").withSubsectionId("content"),
                                fieldWithPath("accessToken").type(STRING).description("엑세스 토큰 값")
                        )
                ))
        ;
    }
}
