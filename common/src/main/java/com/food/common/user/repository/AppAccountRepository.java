package com.food.common.user.repository;

import com.food.common.user.domain.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {
}
