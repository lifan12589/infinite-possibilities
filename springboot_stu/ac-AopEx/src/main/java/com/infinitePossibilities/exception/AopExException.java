package com.infinitePossibilities.exception;

import com.infinitePossibilities.enums.ResultEnum;


public class AopExException extends RuntimeException{

    private Integer code;

    public AopExException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
