package com.food.common.user.business.external;

import com.food.common.user.business.external.dto.LogInOutLogRequest;

public interface LogInOutLogCommonService {
    void saveLoginLog(LogInOutLogRequest request);
    void saveLogoutLog(LogInOutLogRequest request);
}
