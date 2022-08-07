package com.food.common.store.repository;

import com.food.common.store.domain.StoreAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreAddressRepository extends JpaRepository<StoreAddress, Long> {
}
