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
        this.token = details.getTokenValue();
        this.tokenType = details.getTokenType();
        this.serverIp = details.getRemoteAddress();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
