package com.raon.domain.user;

import com.raon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWriter {

    private final UserMapper userMapper;

    public UserEntity create(UserEntity userEntity) {
        userMapper.create(userEntity);
        return userEntity;
    }
}
