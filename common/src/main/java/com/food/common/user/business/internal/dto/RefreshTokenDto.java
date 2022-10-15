package com.food.common.user.business.internal.dto;

import com.food.common.user.domain.RefreshToken;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class RefreshTokenDto {
    private final String value;
    private final UserDto user;
    private final LocalDateTime issuedDate;
    private final LocalDateTime expiredDate;

    public RefreshTokenDto(final RefreshToken refreshToken) {
        this.value = refreshToken.getValue();
        this.user = new UserDto(refreshToken.getUser());
        this.issuedDate = refreshToken.getIssuedDate();
        this.expiredDate = refreshToken.getExpiredDate();
    }

    public boolean hasBeenPassedExpiredDate() {
        return LocalDateTime.now().isAfter(expiredDate);
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public Long getUserId() {
        return user.getId();
    }
}
