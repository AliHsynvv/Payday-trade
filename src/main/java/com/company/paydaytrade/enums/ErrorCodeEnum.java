package com.company.paydaytrade.enums;

import lombok.Getter;
@Getter
public enum ErrorCodeEnum {
    USER_NOT_FOUND(1001, "Cannot find user with given id "),
    USERSTOCK_NOT_FOUND(1002,"Cannot find user stock with given id "),
    VALIDATION_ERROR(1003,"Validation errors"),
    UNKNOWN_ERROR(1000,"Unknown error");
    private final int code;
    private final String message;
    ErrorCodeEnum(int code,String message) {
        this.code=code;
        this.message = message;
    }
}
