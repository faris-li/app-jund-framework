package com.jund.framework.security.authentication;

import com.jund.framework.security.authentication.details.AccessToken;
import com.jund.framework.security.authentication.details.AuthUser;
import com.jund.framework.security.authentication.strategy.AuthenticationStrategy;
import com.jund.framework.security.authentication.strategy.impl.OAuth2AuthenticationStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;

/**
 * Created by 段志军 on 2017/9/5.
 */
public class AuthenticationHolder {

    private static String strategyName = System.getProperty("jund.security.strategy");

    private static AuthenticationStrategy authenticationStrategy;

    static {
        initialize();
    }

    private static void initialize(){
        if(StringUtils.isEmpty(strategyName)){
            authenticationStrategy = new OAuth2AuthenticationStrategy();
        }else{
            try{
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                authenticationStrategy = (AuthenticationStrategy)customStrategy.newInstance();
            } catch (Exception ex){
                ReflectionUtils.handleReflectionException(ex);
            }
        }
    }

    public static String getUsernanme(){
        return getAuthUser().getUsername();
    }

    public static AuthUser getAuthUser(){
        return authenticationStrategy.getAuthenticationDetails().getAuthUser();
    }

    public static AccessToken getAccessToken(){
        return authenticationStrategy.getAuthenticationDetails().getAccessToken();
    }
}
