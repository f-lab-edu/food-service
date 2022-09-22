package com.food.common.user.business.dto.response.refreshTokenDomain;

import com.food.common.user.domain.RefreshToken;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RefreshTokenCreate {
    private final String value;
    private final LocalDateTime issuedDate;
    private final LocalDateTime expiredDate;

    public RefreshTokenCreate(RefreshToken refreshToken) {
        this.value = refreshToken.getValue();
        this.issuedDate = refreshToken.getIssuedDate();
        this.expiredDate = refreshToken.getExpiredDate();
    }
}
