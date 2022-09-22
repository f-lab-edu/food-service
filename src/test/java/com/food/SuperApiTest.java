package com.food;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.UserRepository;
import com.food.mock.user.MockAppAccount;
import com.food.mock.user.MockUser;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@Transactional
public class SuperApiTest {
    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    protected void setup(RestDocumentationContextProvider restDocumentation) {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity())
                .build();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    protected MockAccount createMockAccount() {
        User mockUser = MockUser.builder().build();
        Long userId = userRepository.save(mockUser).getId();
        AppAccount account = MockAppAccount.builder()
                .user(mockUser)
                .build();
        appAccountRepository.save(account);

        return new MockAccount(account.getLoginId(), account.getPassword(), userId);
    }

    @Getter
    protected static class MockAccount {
        private String loginId;
        private String password;
        private Long userId;

        public MockAccount(String loginId, String password, Long userId) {
            this.loginId = loginId;
            this.password = password;
            this.userId = userId;
        }
    }
}
