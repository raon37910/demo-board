package com.raon.service.user;

import java.time.LocalDateTime;

public record UserSignupResult(
        String email,
        String role,
        LocalDateTime createdAt
) {
}
