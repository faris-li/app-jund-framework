package com.jund.framework.security;

/**
 * Created by 段志军 on 2017/9/5.
 */
public class SecConst {
    public static class AuthReturnCode {
        public static final String TOKEN_NOFOUND = "2001";
        public static final String AUTH_TYPE_ERROR = "2002";
        public static final String UNAUTHORIZED = "2003";
    }

    public static class Token {
        public static final String TOKEN_TYPE = "Bearer";
        public static final String AUTHORIZATION_HEADER = "Authentication";
    }
}
