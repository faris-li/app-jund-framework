package com.jund.framework.security.feign;


import com.jund.framework.security.SecConst;
import com.jund.framework.security.authentication.AuthenticationHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Created by 段志军 on 2017/9/6.
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = AuthenticationHolder.getAccessToken().getToken();
        String tokenHeader = String.format("%s %s", SecConst.Token.TOKEN_TYPE, accessToken);
        requestTemplate.header(SecConst.Token.AUTHORIZATION_HEADER, tokenHeader);

    }
}
