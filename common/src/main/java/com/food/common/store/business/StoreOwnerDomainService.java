package com.food.common.store.business;

import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;

import java.util.Optional;

public interface StoreOwnerDomainService {
    Optional<StoreOwner> findByUser(User user);
}
