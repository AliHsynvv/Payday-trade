package com.company.paydaytrade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserStockNotFoundException extends RuntimeException {
    public UserStockNotFoundException (String message){
        super(message);
    }
}
