package com.food.auth.config;

import com.food.auth.apiscanner.ApiScanner;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;
import java.util.Set;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final ApiScanner scanner;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(STATELESS);

        Map<Role, Set<String>> requestPaths = scanner.scanRequestMethods();
        http.authorizeHttpRequests(
                (auth) -> auth
                        .antMatchers(String.valueOf(requestPaths.get(Role.CUSTOMER))).hasRole(Role.CUSTOMER.name())
                        .antMatchers(String.valueOf(requestPaths.get(Role.STORE_OWNER))).hasRole(Role.STORE_OWNER.name())
                        .antMatchers(String.valueOf(requestPaths.get(Role.MANAGER))).hasRole(Role.MANAGER.name())
                        .antMatchers(String.valueOf(requestPaths.get(Role.ALL))).permitAll()
                    .anyRequest().authenticated());

        return http.build();
    }
}
