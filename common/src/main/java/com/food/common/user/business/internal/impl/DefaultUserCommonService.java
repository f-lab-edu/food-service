package com.food.common.user.business.internal.impl;

import com.food.common.store.business.internal.StoreOwnerEntityService;
import com.food.common.store.domain.StoreOwner;
import com.food.common.user.business.internal.UserCommonService;
import com.food.common.user.business.internal.dto.UserDto;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultUserCommonService implements UserCommonService {
    private final UserEntityService userEntityService;
    private final StoreOwnerEntityService storeOwnerService;

    public UserDto findById(Long id) {
        User user = userEntityService.findById(id);
        return new UserDto(user);
    }

    public Role findRoleById(Long id) {
        User user = userEntityService.findById(id);

        Optional<StoreOwner> foundStoreOwner = storeOwnerService.findByUser(user);
        return foundStoreOwner.isPresent() ? Role.STORE_OWNER : Role.CUSTOMER;
    }
}

