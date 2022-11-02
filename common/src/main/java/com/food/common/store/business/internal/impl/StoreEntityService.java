package com.food.common.store.business.internal.impl;

import com.food.common.store.domain.Store;
import com.food.common.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StoreEntityService {
    private final StoreRepository storeRepository;

    public Store findById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Store가 존재하지 않습니다. id=" + id));
    }
}
