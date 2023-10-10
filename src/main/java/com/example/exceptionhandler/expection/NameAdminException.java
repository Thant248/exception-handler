package com.example.exceptionhandler.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Name;

public class NameAdminException  extends ResponseStatusException {

    public NameAdminException(){
        super(HttpStatus.BAD_REQUEST, "cannot be admin!");
    }
}
