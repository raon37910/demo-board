package com.raon.domain.user;

import com.raon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserMapper userMapper;

    public boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }
}
