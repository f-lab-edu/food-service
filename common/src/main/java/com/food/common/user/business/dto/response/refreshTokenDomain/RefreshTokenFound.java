package com.food.common.user.business.dto.response.refreshTokenDomain;

import com.food.common.user.domain.RefreshToken;
import com.food.common.user.domain.User;

import java.time.LocalDateTime;

public class RefreshTokenFound {
    private final String value;
    private final Owner owner;
    private final LocalDateTime expiredDate;

    public RefreshTokenFound(final RefreshToken refreshToken) {
        this.value = refreshToken.getValue();
        this.owner = new Owner(refreshToken.getUser());
        this.expiredDate = refreshToken.getExpiredDate();
    }

    public boolean hasBeenPassedExpiredDate() {
        return LocalDateTime.now().isAfter(expiredDate);
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public Long getOwnerId() {
        return owner.getUserId();
    }

    private static class Owner {
        private final Long userId;

        public Owner(final User user) {
            this.userId = user.getId();
        }

        private Long getUserId() {
            return userId;
        }
    }
}
