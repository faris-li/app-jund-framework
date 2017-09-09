package com.jund.framework.security.authentication.strategy;

import com.jund.framework.security.authentication.details.AuthenticationDetails;
import org.springframework.security.core.Authentication;

/**
 * Created by 段志军 on 2017/9/5.
 */
public interface AuthenticationStrategy {

    boolean checkAuthentication();

    Authentication getAuthentication();

    AuthenticationDetails getAuthenticationDetails();
}
