package com.food.api.auth;

import com.food.SuperIntegrationTest;
import com.food.auth.presentation.dto.LoginRequest;
import com.food.common.user.enumeration.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.RestDocumentationContextProvider;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TokenIssueApiTest extends SuperIntegrationTest {
    private final String DOCUMENT_AUTH = "auth/create-token/";

    @BeforeEach
    protected void setup(RestDocumentationContextProvider restDocumentation) {
        super.setup(restDocumentation);
    }

    @Test
    void shouldIssueToken_whenRequestWithCorrectLoginIdAndPassword() throws Exception {
        LoginRequest request = new LoginRequest(mockAccount.getLoginId(), mockAccount.getPassword(), AccountType.APP);
        mvc.perform(post("/auth/login")
                        .header(ACCEPT, APPLICATION_JSON_VALUE)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(true))
                .andDo(document(DOCUMENT_AUTH +"success",
                        requestHeaders(
                                headerWithName(ACCEPT).description("accept"),
                                headerWithName(CONTENT_TYPE).description("content type")
                        ),
                        requestFields(
                                fieldWithPath("loginId").type(STRING).description("로그인 아이디"),
                                fieldWithPath("password").type(STRING).description("로그인 비밀번호"),
                                fieldWithPath("type").type(STRING).description("로그인 타입")
                        ),
                        responseFields(
                                fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
                                subsectionWithPath("content").description("내용")
                        ),
                        responseFields(
                                beneathPath("content").withSubsectionId("content"),
                                fieldWithPath("accessToken").type(STRING).description("엑세스 토큰 값"),
                                fieldWithPath("refreshToken").type(STRING).description("리프레시 토큰 값"),
                                fieldWithPath("userId").type(NUMBER).description("로그인 유저 고유번호"),
                                fieldWithPath("nickname").type(STRING).description("로그인 유저의 닉네임"),
                                fieldWithPath("loginId").type(STRING).description("로그인 아이디")
                        )
                ))
        ;
    }
}
