package com.food.common.store.business.internal;

import com.food.common.store.business.internal.dto.StoreDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public interface StoreCommonService {
    Optional<StoreDto> findById(@NotNull @Positive Long storeId);
}
