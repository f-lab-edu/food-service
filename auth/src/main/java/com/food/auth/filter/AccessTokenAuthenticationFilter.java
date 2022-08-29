package com.food.auth.filter;

import com.food.auth.filter.dto.AuthenticatedUser;
import com.food.auth.provider.AccessTokenProvider;
import com.food.auth.provider.dto.AccessToken;
import com.food.auth.provider.dto.AccessTokenContent;
import com.food.auth.provider.dto.AccessTokenValidationResult;
import com.food.user.service.UserFindService;
import com.food.user.service.dto.AccountFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class AccessTokenAuthenticationFilter extends OncePerRequestFilter {
    private final AccessTokenProvider accessTokenProvider;
    private final UserFindService userService;

    private Optional<AccessToken> extractTokenFromBearerToken(String authorization) {
        if (hasText(authorization) && Pattern.matches("^Bearer .*", authorization)) {
            String value = authorization.replaceAll("^Bearer( )*", "");

            return hasText(value) ? Optional.of(new AccessToken(value)) : Optional.empty();
        }

        return Optional.empty();
    }

    private Authentication getAuthentication(AccessTokenContent content) {
        AccountFindResponse account = userService.findAccountByUserId(content.getUserId());

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(account);
        return new UsernamePasswordAuthenticationToken(authenticatedUser, "", authenticatedUser.getAuthorities());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AccessToken accessToken = extractTokenFromBearerToken(request.getHeader(HttpHeaders.AUTHORIZATION))
                .orElseThrow(() -> new IllegalArgumentException("토큰 값이 비어있습니다.") );

        AccessTokenValidationResult validationResult = accessTokenProvider.validate(accessToken);
        if (validationResult.isFailed()) {
            throw new IllegalArgumentException(validationResult.getFailedMessage());
        }

        AccessTokenContent content = accessTokenProvider.getContent(accessToken);
        Authentication authentication = getAuthentication(content);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
