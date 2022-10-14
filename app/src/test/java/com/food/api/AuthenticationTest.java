package com.food.api;

import com.food.SuperIntegrationTest;
import com.food.auth.filter.dto.AuthenticatedUser;
import com.food.auth.provider.AccessTokenProvider;
import com.food.auth.provider.dto.AccessToken;
import com.food.common.user.business.external.dto.AppAccountDto;
import com.food.common.user.business.service.response.accountFind.AccountFindResponse;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.Role;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthenticationTest extends SuperIntegrationTest {
    @Autowired
    private AccessTokenProvider provider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Test
    @Disabled
    void success() throws Exception {
        User user = userRepository.save(User.create("username"));
        AppAccount account = appAccountRepository.save(AppAccount.create("testuser@email.com", "password", user));

        AccessToken accessToken = provider.create(new AuthenticatedUser(new AccountFindResponse(new AppAccountDto(account), Role.STORE_OWNER)).getUserId());

        mvc.perform(get("/accounts/test")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getValue()))
                .andExpect(status().isNoContent())
                .andDo(print())

        ;
    }
}
