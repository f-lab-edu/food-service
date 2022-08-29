package com.food.common.user.repository;

import com.food.common.store.domain.StoreOwner;
import com.food.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {
    Optional<StoreOwner> findByUser(User user);
}
