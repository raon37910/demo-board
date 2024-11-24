package com.raon.controller.user;

import com.raon.controller.user.request.UserSignupRequest;
import com.raon.controller.user.response.UserSignupResponse;
import com.raon.service.user.UserService;
import com.raon.service.user.UserSignupResult;
import com.raon.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserSignupResponse> signUp(@RequestBody UserSignupRequest userSignupRequest) {
        UserSignupResult userSignupResult = userService.signUp(userSignupRequest.toUserSignupInfo());
        return ApiResponse.success(new UserSignupResponse(
                userSignupRequest.email(),
                userSignupResult.role(),
                userSignupResult.createdAt())
        );
    }
}
