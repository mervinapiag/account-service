package com.exam.account_service.api.account.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {

        super("Email already registered: " + email);
    }
}
