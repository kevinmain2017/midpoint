/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fis.com.vn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Minhgalc
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushBrandnameOTP {
    
    private String access_token;
    private String session_id;
    private String BrandName;
    private String Phone;
    private String Message;

    @JsonProperty("access_token")
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    
    @JsonProperty("session_id")
    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    @JsonProperty("BrandName")
    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    @JsonProperty("Phone")
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
    
    
    
}
