package com.jund.framework.mvc.response;

/**
 * Created by 段志军 on 2017/9/4.
 */
public class RestException extends RuntimeException {

    private String code;
    private String data;

    public RestException(String code, String message) {
        this(code, message, null);
        this.code = code;
    }

    public RestException(String code, String message, String data) {
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
