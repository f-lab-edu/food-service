package com.food.auth.business;

import com.food.common.user.business.AuthenticatedUserFindService;
import com.food.common.user.dto.RequestUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticatedUserFindService implements AuthenticatedUserFindService {

    @Override
    public RequestUser findRequestUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof RequestUser)) {
            throw new IllegalArgumentException("현재 로그인 유저를 찾을 수 없습니다.");
        }

        return (RequestUser) authentication.getPrincipal();
    }
}
