package com.raon.controller.user.request;

import com.raon.service.user.model.UserSigninInfo;

public record UserSigninRequest(
        String email,
        String password
) {

    public UserSigninInfo toUserSigninInfo() {
        return new UserSigninInfo(email, password);
    }
}
