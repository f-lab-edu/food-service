package com.food.common.user.repository;


import com.food.common.user.domain.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
}
