package com.example.exceptionhandler.expection;

import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, messages(status.value(), ex), headers, status, request);
    }

    private ApiError messages(int status, Exception e){
        String messages = e == null ? "Unknown Error"  : e.getMessage();
        String devMessages = ExceptionUtils.getMessage(e);
        return  new ApiError(status, messages, devMessages);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return  handleExceptionInternal(ex, messages(status.value(), ex), headers, status, request);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class, ConstraintViolationException.class})
    protected ResponseEntity<Object> handleExceptionInternal(RuntimeException ex,WebRequest request) {
        return  handleExceptionInternal
                (ex, messages(HttpStatus.BAD_GATEWAY.value(), ex), new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
    }
}
