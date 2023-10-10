package com.example.exceptionhandler.expection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiError {

    private int status;
    private String message;
    private String developerMessages;

    public ApiError() {
    }

    public ApiError(int status, String message, String developerMessages) {
        this.status = status;
        this.message = message;
        this.developerMessages = developerMessages;
    }
}
