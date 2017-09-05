package com.jund.framework.security.authentication.details;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Created by zhijund on 2017/9/2.
 */
public class AccessToken {

    private String token;

    private String tokenType;

    private String serverIp;

    public AccessToken(OAuth2AuthenticationDetails details){

    }

}
