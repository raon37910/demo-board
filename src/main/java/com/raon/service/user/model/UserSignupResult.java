package com.raon.service.user.model;

import java.time.LocalDateTime;

public record UserSignupResult(
        String email,
        String role,
        LocalDateTime createdAt
) {
}
