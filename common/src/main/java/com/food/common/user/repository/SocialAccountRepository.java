package com.food.common.user.repository;

import com.food.common.user.domain.SocialAccount;
import com.food.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
    Optional<SocialAccount> findByUser(User user);
}
