package com.example.exceptionhandler.expection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class EmployeeException {


    private final static  long serialVersionYID = 1L;

    private final Type type;

    private final List<String> messages;


    public enum Type {
        BUSINESS, VALIDATION, PLATFORM
    }
}
