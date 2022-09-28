package com.food.common.user.business.internal;

import com.food.common.user.domain.User;
import com.food.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Transactional
@Service
public class UserEntityService {
    private final UserRepository userRepository;

    public User findById(@NotNull Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user를 찾을 수 없습니다. id="+id));
    }
}
