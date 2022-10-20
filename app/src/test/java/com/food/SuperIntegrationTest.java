package com.food;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.auth.provider.AccessTokenProvider;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.mock.order.MockOrderFactory;
import com.food.mock.store.MockStoreFactory;
import com.food.mock.store.MockStoreOwnerFactory;
import com.food.mock.user.MockAccountFactory;
import com.food.mock.user.MockPointFactory;
import com.food.mock.user.MockUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Import({
        MockUserFactory.class,
        MockAccountFactory.class,
        MockPointFactory.class,
        MockStoreOwnerFactory.class,
        MockStoreFactory.class,
        MockOrderFactory.class
})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@Transactional
public class SuperIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected User mockUser;
    protected AppAccount mockAccount;

    @Autowired
    protected MockUserFactory userFactory;

    @Autowired
    protected MockAccountFactory accountFactory;

    @Autowired
    protected MockPointFactory pointFactory;

    @Autowired
    protected MockStoreOwnerFactory storeOwnerFactory;

    @Autowired
    protected MockStoreFactory storeFactory;

    @Autowired
    protected MockOrderFactory orderFactory;

    @Autowired
    protected AccessTokenProvider accessTokenProvider;

    @BeforeEach
    protected void setup(RestDocumentationContextProvider restDocumentation) {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity())
                .build();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        mockUser = userFactory.user();
        mockAccount = accountFactory.appAccount(mockUser, "test-user123@email.com");
    }

    protected String createAuthentication() {
        return "Bearer " + accessTokenProvider.create(mockUser.getId()).getValue();
    }
}

