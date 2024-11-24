package com.raon.mapper;

import com.raon.domain.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    boolean existsByEmail(String email);

    int create(UserEntity user);
}
