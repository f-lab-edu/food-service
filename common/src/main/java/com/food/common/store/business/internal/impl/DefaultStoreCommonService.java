package com.food.common.store.business.internal.impl;

import com.food.common.store.business.internal.StoreCommonService;
import com.food.common.store.business.internal.dto.StoreDto;
import com.food.common.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class DefaultStoreCommonService implements StoreCommonService {
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<StoreDto> findById(Long storeId) {
        return storeRepository.findById(storeId)
                .map(StoreDto::new);
    }
}
