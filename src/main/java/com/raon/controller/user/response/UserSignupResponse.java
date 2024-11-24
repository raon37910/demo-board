package com.raon.controller.user.response;

import java.time.LocalDateTime;

public record UserSignupResponse(
        String email,
        String role,
        LocalDateTime createdAt
){
}
