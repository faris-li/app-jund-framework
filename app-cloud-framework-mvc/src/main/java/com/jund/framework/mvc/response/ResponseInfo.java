package com.jund.framework.mvc.response;

import com.jund.framework.mvc.MvcConst;

/**
 * Created by zhijund on 2017/9/3.
 */
public class ResponseInfo<T> {

    private String code;

    private String message;

    private T data;

    public ResponseInfo() {
        this(MvcConst.ReturnCode.OK,null);
    }

    public ResponseInfo(T data) {
        this(MvcConst.ReturnCode.OK,  data);
    }

    public ResponseInfo(String code, T data) {
        this(code, "成功", data);
    }

    public ResponseInfo(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
