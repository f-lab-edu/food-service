package com.food.mock.store;

import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;
import com.food.common.user.repository.StoreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockStoreOwnerFactory {

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public StoreOwner storeOwner(User user) {
        StoreOwner storeOwner = MockStoreOwner.builder()
                .user(user)
                .build();

        return storeOwnerRepository.save(storeOwner);
    }
}
