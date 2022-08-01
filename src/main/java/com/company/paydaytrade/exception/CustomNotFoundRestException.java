package com.company.paydaytrade.exception;

import com.company.paydaytrade.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class CustomNotFoundRestException extends RuntimeException {
    private final String message;
    private final int code;

    public CustomNotFoundRestException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.message = errorCodeEnum.getMessage();
        this.code = errorCodeEnum.getCode();
    }

}
