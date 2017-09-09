package com.jund.framework.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by killer_duan on 2015/5/27.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final String[] exceptions = new String[]{
            "/v2/api-docs",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/configuration/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .mvcMatchers(exceptions).permitAll()
                .anyRequest().authenticated();
    }

}
