package com.food.common.user.repository;

import com.food.common.user.domain.LogInOutLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginInOutRepository extends JpaRepository<LogInOutLog, LogInOutLog.LogInOutLogPK> {
}
