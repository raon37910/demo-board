package com.raon.controller.user;

import com.raon.controller.user.request.UserSigninRequest;
import com.raon.controller.user.request.UserSignupRequest;
import com.raon.controller.user.response.UserSignupResponse;
import com.raon.controller.user.response.UserSinginResponse;
import com.raon.service.user.UserService;
import com.raon.service.user.model.UserSigninResult;
import com.raon.service.user.model.UserSignupResult;
import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import com.raon.support.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
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
    private static final String LOGIN_USER = "LOGIN_USER";

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

    @PostMapping("sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserSinginResponse> signIn(
            @RequestBody UserSigninRequest userSigninRequest,
            HttpSession session
    ) {
        UserSigninResult userSigninResult = userService.signIn(userSigninRequest.toUserSigninInfo());
        session.setAttribute(LOGIN_USER, userSigninResult);

        return ApiResponse.success(new UserSinginResponse(
                userSigninRequest.email(),
                userSigninResult.role())
        );
    }

    @PostMapping("sign-out")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> signOut(HttpSession session) {
        session.removeAttribute(LOGIN_USER);
        return ApiResponse.success(null);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> delete(HttpSession session) {
        Object sessionData = session.getAttribute(LOGIN_USER);
        if (sessionData instanceof UserSigninResult signinUser) {
            userService.deleteUser(signinUser.email());
            return ApiResponse.success(null);
        } else {
            throw new BoardException(ErrorType.VALIDATION_ERROR);
        }
    }
}
