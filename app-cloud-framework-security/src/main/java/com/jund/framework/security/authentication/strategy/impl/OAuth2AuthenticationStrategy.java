package com.jund.framework.security.authentication.strategy.impl;

import com.jund.framework.mvc.RestConst;
import com.jund.framework.mvc.response.RestException;
import com.jund.framework.security.SecConst;
import com.jund.framework.security.authentication.details.AccessToken;
import com.jund.framework.security.authentication.details.AuthUser;
import com.jund.framework.security.authentication.details.AuthenticationDetails;
import com.jund.framework.security.authentication.strategy.AuthenticationStrategy;
import com.jund.framework.security.util.AuthenticationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Map<String,Object> props = (Map<String,Object>)oauth2.getUserAuthentication();
        if (!props.containsKey("principal")){
            throw new RestException(RestConst.ReturnCode.USER_NOFOUND,"用户不存在！");
        }

        Map<String,Object> principal = (Map<String, Object>) props.get("principal");

        Object authoritiesValue = principal.get("authorities");
        List<Map<String,String>> grantedAuthorities = (List<Map<String,String>>) authoritiesValue;
        if (grantedAuthorities.isEmpty()){
            throw new RestException(SecConst.AuthReturnCode.UNAUTHORIZED,"用户为授权！");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Map<String,String> grantedMap:grantedAuthorities){
            String grantValue = grantedMap.get("authority");
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(grantValue);
            authorities.add(grantedAuthority);
        }

        String username = (String)principal.get("username");
        String password = (String)principal.get("password");
        AuthUser authUser = new AuthUser(username,password,authorities);
        authUser.setOrgCode((String)principal.get("orgCode"));
        authUser.setOrgName((String)principal.get("orgName"));
        return authUser;
    }
}
