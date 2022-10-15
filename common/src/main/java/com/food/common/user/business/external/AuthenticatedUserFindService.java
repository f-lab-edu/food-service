package com.food.common.user.business.external;

import com.food.common.user.business.external.model.RequestUser;

public interface AuthenticatedUserFindService {
    RequestUser findRequestUser();
}
