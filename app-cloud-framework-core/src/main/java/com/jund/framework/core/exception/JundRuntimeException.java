package com.jund.framework.core.exception;

/**
 * Created by zhijund on 2017/9/3.
 */
public class JundRuntimeException extends RuntimeException {
    private String code;

    public JundRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
