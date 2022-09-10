package com.food.common.user.business.user;

import com.food.common.user.business.user.impl.response.FoundUser;
import com.food.common.user.domain.User;

public interface UserDomainService {
    FoundUser getFoundUser(User user);
    User findEntityById(Long id);
}
