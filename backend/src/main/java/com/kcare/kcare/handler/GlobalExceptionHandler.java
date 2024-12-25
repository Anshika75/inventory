package com.kcare.kcare.handler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(LockedException.class)
        public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.ACCOUNT_LOCKED.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.ACCOUNT_LOCKED.getDescription())
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(DisabledException.class)
        public ResponseEntity<ExceptionResponse> handleException(DisabledException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.ACCOUNT_DISABLED.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.ACCOUNT_DISABLED.getDescription())
                                                .error(exp.getMessage())
                                                .build());

        }

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                                ExceptionResponse.builder()
                                                .businessErrorCode(businessErrorCode.BAD_CREDENTIALS.getCode())
                                                .businessErrorDescription(
                                                                businessErrorCode.BAD_CREDENTIALS.getDescription())
                                                .error(businessErrorCode.BAD_CREDENTIALS.getDescription())
                                                .build());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp) {
                Set<String> errors = new HashSet<>();
                exp.getBindingResult().getAllErrors().forEach(
                                error -> {
                                        var errorMessage = error.getDefaultMessage();
                                        errors.add(errorMessage);
                                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .validationErrors(errors)
                                                .build());
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException exp) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription(exp.getMessage())
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
                exp.printStackTrace();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Internal error, Contact the admin")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<ExceptionResponse> handleException(DuplicateEmailException exp) {
                exp.printStackTrace();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Email already in use")
                                                .error(exp.getMessage())

                                                .build());
        }

        @ExceptionHandler(AccountNotVerfiedException.class)
        public ResponseEntity<ExceptionResponse> handleException(AccountNotVerfiedException exp) {
                exp.printStackTrace();

                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Account Not Varified")
                                                .error(exp.getMessage())

                                                .build());
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ExceptionResponse> handleException(AccessDeniedException exp) {
                exp.printStackTrace();
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription(
                                                                "Access Denied: You do not have the required permissions to access this resource.")
                                                .error(exp.getMessage())
                                                .build());
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ExceptionResponse> handleException(EntityNotFoundException exp) {
                exp.printStackTrace();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                ExceptionResponse.builder()
                                                .businessErrorDescription("Resource Not Available in Database")
                                                .error(exp.getMessage())
                                                .build());
        }

}