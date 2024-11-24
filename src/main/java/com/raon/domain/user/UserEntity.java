package com.raon.domain.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserEntity {
    private Integer id;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    // TODO ENUM Base 리팩토링
    private String role;

    public static UserEntity createDefaultUser(String email, String password) {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .role(UserConstant.DEFAULT_USER)
                .createdAt(LocalDateTime.now()) // TODO 시간을 외부에서 받아야 테스트하기 편하긴 한데 편의를 위해 일단 넘김.
                .build();
    }
}
