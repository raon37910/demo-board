package com.raon.exception.user;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException() {
        super("중복된 이메일입니다.");
    }
}
