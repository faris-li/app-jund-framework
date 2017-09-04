package com.jund.framework.core.exception;

/**
 * Created by zhijund on 2017/9/3.
 */
public class JundRuntimeException extends RuntimeException {
    private String code;
    private String data;

    public JundRuntimeException(String code, String message) {
        this(code, message, null);
        this.code = code;
    }

    public JundRuntimeException(String code, String message, String data) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
