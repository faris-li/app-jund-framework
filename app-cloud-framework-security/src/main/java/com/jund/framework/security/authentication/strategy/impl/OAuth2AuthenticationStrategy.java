package com.jund.framework.security.authentication.strategy.impl;

import com.jund.framework.mvc.response.RestException;
import com.jund.framework.security.SecConst;
import com.jund.framework.security.authentication.details.AccessToken;
import com.jund.framework.security.authentication.details.AuthUser;
import com.jund.framework.security.authentication.details.AuthenticationDetails;
import com.jund.framework.security.authentication.strategy.AuthenticationStrategy;
import com.jund.framework.security.util.AuthenticationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Created by 段志军 on 2017/9/5.
 */
public class OAuth2AuthenticationStrategy implements AuthenticationStrategy{
    @Override
    public boolean checkAuthentication() {
        return AuthenticationUtil.getAuthentication() instanceof OAuth2Authentication;
    }

    @Override
    public Authentication getAuthentication() {
        if(checkAuthentication()){
            AuthenticationUtil.getAuthentication();
        }
        throw new RestException(SecConst.AuthReturnCode.AUTH_TYPE_ERROR,"不是OAuth认证！");
    }

    @Override
    public AuthenticationDetails getAuthenticationDetails() {
        OAuth2Authentication oauth2 = (OAuth2Authentication)getAuthentication();
        AuthenticationDetails details = new AuthenticationDetails();
        details.setAccessToken(getAccessToken(oauth2));
        details.setAuthUser(getAuthUser(oauth2));
        return null;
    }

    private AccessToken getAccessToken(OAuth2Authentication oauth2) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)oauth2.getDetails();
        if (StringUtils.isEmpty(details.getTokenValue())) {
            throw new RestException(SecConst.AuthReturnCode.TOKEN_NOFOUND,"Token值为空");
        }
        return new AccessToken(details);
    }

    public AuthUser getAuthUser(OAuth2Authentication oauth2) {
        return authUser;
    }
}
