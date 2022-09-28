package com.food.common.user.business.external;

import com.food.common.user.business.external.dto.AppAccountDto;
import com.food.common.user.business.external.dto.SocialAccountDto;

import java.util.Optional;

public interface AccountCommonService {
    Optional<AppAccountDto> findAppAccountByUserId(Long userId);
    Optional<SocialAccountDto> findSocialUserIdByUserId(Long userId);
    Optional<AppAccountDto> findAppAccountByLoginId(String loginId);
    Optional<SocialAccountDto> findSocialAccountByLoginId(String loginId);

}
