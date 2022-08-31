package com.food.auth.integration.authorize;

import com.food.auth.filter.dto.AuthenticatedUser;
import com.food.auth.integration.SecurityApiTest;
import com.food.auth.provider.AccessTokenProvider;
import com.food.auth.provider.dto.AccessToken;
import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.Role;
import com.food.common.user.repository.AppAccountRepository;
import com.food.common.user.repository.StoreOwnerRepository;
import com.food.common.user.repository.UserRepository;
import com.food.common.user.service.account.response.FoundAppAccount;
import com.food.common.user.service.user.response.FoundUser;
import com.food.user.service.dto.AccountFindResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthenticationTest extends SecurityApiTest {
    @Autowired
    private AccessTokenProvider provider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    @Test
    void success() throws Exception {
        User user = userRepository.save(User.create("username"));
        AppAccount account = appAccountRepository.save(AppAccount.create("testuser@email.com", "password", user));

        AccessToken accessToken = provider.create(new AuthenticatedUser(new AccountFindResponse(new FoundAppAccount(account, new FoundUser(user, Role.CUSTOMER)))));

        mvc.perform(get("/manage/stores")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getValue()))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    void fail_authorization() throws Exception {
        User user = userRepository.save(User.create("username"));
        AppAccount account = appAccountRepository.save(AppAccount.create("testuser@email.com", "password", user));

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(new AccountFindResponse(new FoundAppAccount(account, new FoundUser(user, Role.CUSTOMER))));
        AccessToken accessToken = provider.create(authenticatedUser);

        mvc.perform(get("/users/manage")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getValue()))
                .andDo(print())
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    void success_authorization() throws Exception {
        User user = userRepository.save(User.create("username"));
        AppAccount account = appAccountRepository.save(AppAccount.create("testuser@email.com", "password", user));
        StoreOwner storeOwner = storeOwnerRepository.save(StoreOwner.create(user));

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(new AccountFindResponse(new FoundAppAccount(account, new FoundUser(user, Role.STORE_OWNER))));
        AccessToken accessToken = provider.create(authenticatedUser);

        mvc.perform(get("/users/manage")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getValue()))
                .andDo(print())
                .andExpect(status().isNoContent())
        ;
    }
}