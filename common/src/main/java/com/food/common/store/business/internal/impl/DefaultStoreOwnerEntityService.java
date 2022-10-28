package com.food.common.store.business.internal.impl;

import com.food.common.store.business.internal.StoreOwnerEntityService;
import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;
import com.food.common.user.repository.StoreOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DefaultStoreOwnerEntityService implements StoreOwnerEntityService {
    private final StoreOwnerRepository storeOwnerRepository;

    public Optional<StoreOwner> findByUser(User user) {
        return storeOwnerRepository.findByUser(user);
    }
}
