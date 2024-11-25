package com.raon.domain.user;

import com.raon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserMapper userMapper;

    public boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }
}
