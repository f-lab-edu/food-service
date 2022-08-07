package com.food.common.user.repository;

import com.food.common.user.domain.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {
}
