package com.kcare.kcare.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Response<T> {

    private T contents;
    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;

}
