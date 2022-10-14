package com.food.api;

import com.food.SuperIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestDocsIndexTest extends SuperIntegrationTest {
    private static final String DIRECT = "common/";

    @Test
    @DisplayName("요청이 성공했을 때의 형식을 가져온다")
    void resultSuccess() throws Exception {
        mvc.perform(get("/api/result/success")
                        .header(ACCEPT, APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(true))
                .andDo(document(DIRECT+"result/success",
                        responseFields(
                                fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
                                fieldWithPath("content").type(OBJECT).description("요청 결과 Object").optional()
                        )
                ))
        ;
    }

    @Test
    @DisplayName("요청이 실패했을 때의 형식을 가져온다")
    void resultFailure() throws Exception {
        mvc.perform(get("/api/result/failure")
                        .header(ACCEPT, APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("success").value(false))
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("message").exists())
                .andDo(document(DIRECT+"result/failure",
                        responseFields(
                                fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
                                fieldWithPath("code").type(STRING).description("실패 코드"),
                                fieldWithPath("message").type(STRING).description("실패 메시지"),
                                fieldWithPath("content").type(OBJECT).description("요청 결과 Object").optional()
                        )
                ))
        ;
    }
}
