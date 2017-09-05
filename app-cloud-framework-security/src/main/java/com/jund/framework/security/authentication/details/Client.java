package com.jund.framework.security.authentication.details;

/**
 * Created by zhijund on 2017/9/2.
 */
public class Client {

    private String grantType;

    private String clientId;

    private String scope;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
