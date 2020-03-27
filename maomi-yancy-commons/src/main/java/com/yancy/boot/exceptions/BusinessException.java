package com.yancy.boot.exceptions;

import com.yancy.boot.enums.ResultCode;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException  {
    protected Integer code;

    protected String message;


    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }
}
