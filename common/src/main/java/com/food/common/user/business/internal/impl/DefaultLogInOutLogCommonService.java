package com.food.common.user.business.internal.impl;

import com.food.common.user.business.internal.LogInOutLogCommonService;
import com.food.common.user.business.internal.dto.LogInOutLogRequest;
import com.food.common.user.domain.LogInOutLog;
import com.food.common.user.repository.LoginInOutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultLogInOutLogCommonService implements LogInOutLogCommonService {
    private final LoginInOutRepository loginInOutRepository;

    @Override
    public void saveLoginLog(LogInOutLogRequest request) {
        LogInOutLog log = LogInOutLog.createLogoutLog(request.getAccountId(), request.getAccountType());
        loginInOutRepository.save(log);
    }

    @Override
    public void saveLogoutLog(LogInOutLogRequest request) {
        LogInOutLog log = LogInOutLog.createLogoutLog(request.getAccountId(), request.getAccountType());
        loginInOutRepository.save(log);
    }
}
