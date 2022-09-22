package com.food.common.user.business;

import com.food.common.user.business.dto.response.userDomain.FoundUser;
import com.food.common.user.domain.User;

public interface UserDomainService {
    FoundUser getFoundUser(User user);
    User findEntityById(Long id);
}
