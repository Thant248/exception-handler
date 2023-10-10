package com.example.exceptionhandler;

import com.example.exceptionhandler.expection.EmployeeException;

import java.lang.invoke.CallSite;
import java.util.List;

public record ErrorResponse(
        EmployeeException.Type type,

        List<String> messages
) {
}
