package com.food.common.user.repository;

import com.food.common.user.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    boolean existsByValue(String value);
}
