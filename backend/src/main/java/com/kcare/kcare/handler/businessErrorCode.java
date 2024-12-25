package com.kcare.kcare.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum businessErrorCode {

    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current Password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "New Password does not Match"),

    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User account is locked"),

    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "User account is disabled"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "Login and / or password is incorrect"),

    RESOURCE_NOT_FOUND(305, HttpStatus.NOT_FOUND, "Resource not found"),
    DUPLICATE_EMAIL_FOUND(306, HttpStatus.CONFLICT, "Duplicate number found"),
    ACCOUNT_NOT_VERIFIED(307, HttpStatus.UNAUTHORIZED, "Account not verified");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    businessErrorCode(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }

}
