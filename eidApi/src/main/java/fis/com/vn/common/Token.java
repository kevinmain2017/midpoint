/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fis.com.vn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Minhgalc
 */
@JsonInclude(Include.NON_NULL)
public class Token {

    private String grant_type;
    private String client_id;
    private String client_secret;
    private String scope;
    private String session_id;

    @JsonProperty("grant_type")
    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
    
    @JsonProperty("client_id")
    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
    
    @JsonProperty("client_secret")
    public String getClient_secret() {
        return client_secret;
    }
    
    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
    
    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @JsonProperty("session_id")
    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
    
}
