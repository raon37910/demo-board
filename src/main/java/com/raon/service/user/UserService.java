package com.raon.service.user;

import com.raon.domain.auth.PasswordEncoder;
import com.raon.domain.user.UserEntity;
import com.raon.domain.user.UserReader;
import com.raon.domain.user.UserWriter;
import com.raon.service.user.model.UserSigninResult;
import com.raon.service.user.model.UserSignupInfo;
import com.raon.service.user.model.UserSignupResult;
import com.raon.service.user.model.UserSigninInfo;
import com.raon.support.error.BoardException;
import com.raon.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserWriter userWriter;
    private final PasswordEncoder passwordEncoder;

    public UserSignupResult signUp(UserSignupInfo info) {
        if (userReader.existsByEmail(info.email())) {
            throw new BoardException(ErrorType.VALIDATION_ERROR);
        }

        final String encodedPassword = passwordEncoder.encode(info.password());
        UserEntity newUser = UserEntity.createDefaultUser(info.email(), encodedPassword);
        userWriter.create(newUser);

        return new UserSignupResult(newUser.getEmail(), newUser.getRole(), newUser.getCreatedAt());
    }

    public UserSigninResult signIn(UserSigninInfo info) {
        UserEntity user = userReader.findByEmail(info.email())
                .orElseThrow(() -> new BoardException(ErrorType.VALIDATION_ERROR));

        if (!passwordEncoder.matches(info.password(), user.getPassword())) {
            throw new BoardException(ErrorType.VALIDATION_ERROR);
        }

        return new UserSigninResult(user.getEmail(), user.getRole());
    }

    public void deleteUser(String email) {
        userWriter.deleteByEmail(email);
    }
}
