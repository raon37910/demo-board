package com.raon.controller.user.request;

import com.raon.service.user.model.UserSignupInfo;

public record UserSignupRequest(
        String email,
        String password
) {
    public UserSignupInfo toUserSignupInfo() {
        return new UserSignupInfo(email, password);
    }
}
