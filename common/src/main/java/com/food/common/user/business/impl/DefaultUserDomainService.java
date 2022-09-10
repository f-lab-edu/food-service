package com.food.common.user.business.impl;

import com.food.common.store.domain.StoreOwner;
import com.food.common.store.service.storeOwner.StoreOwnerDomainService;
import com.food.common.user.business.UserDomainService;
import com.food.common.user.domain.User;
import com.food.common.user.enumeration.Role;
import com.food.common.user.repository.UserRepository;
import com.food.common.user.business.dto.response.userDomain.FoundUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultUserDomainService implements UserDomainService {
    private final UserRepository userRepository;
    private final StoreOwnerDomainService storeOwnerService;

    public FoundUser getFoundUser(@NotNull User user) {
        return new FoundUser(user, findRole(user));
    }

    public User findEntityById(@NotNull Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user를 찾을 수 없습니다. id="+id));
    }

    private Role findRole(User user) {
        Optional<StoreOwner> foundStoreOwner = storeOwnerService.findByUser(user);
        return foundStoreOwner.isPresent() ? Role.STORE_OWNER : Role.CUSTOMER;
    }
}

