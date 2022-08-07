package com.food.common.store.repository;

import com.food.common.store.domain.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreImageRepository extends JpaRepository<StoreImage, StoreImage.MultiplePk> {
}
