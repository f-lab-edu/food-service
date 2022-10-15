package com.food.common.user.business.internal;

import com.food.common.user.business.internal.dto.LogInOutLogRequest;

public interface LogInOutLogCommonService {
    void saveLoginLog(LogInOutLogRequest request);
    void saveLogoutLog(LogInOutLogRequest request);
}
