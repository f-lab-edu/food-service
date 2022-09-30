package com.food.common.user.repository;

import com.food.common.user.domain.AppAccount;
import com.food.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
    Optional<AppAccount> findByUser(User user);
    Optional<AppAccount> findByLoginId(String loginId);
}
