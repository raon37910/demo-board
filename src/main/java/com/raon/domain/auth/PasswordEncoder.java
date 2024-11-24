package com.raon.domain.auth;

public interface PasswordEncoder {
    String encode(String password);
}
