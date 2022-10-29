package com.food.mock.store;

import com.food.common.store.domain.Store;
import com.food.common.store.domain.StoreOwner;
import com.food.common.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MockStoreFactory {

    @Autowired
    private StoreRepository storeRepository;

    /****************************************************************************
     * Create
     ***************************************************************************/

    public Store store(StoreOwner owner) {
        Store store = MockStore.builder()
                .owner(owner)
                .build();

        return storeRepository.save(store);
    }

    public Store store(Store store) {
        return storeRepository.save(store);
    }
}
