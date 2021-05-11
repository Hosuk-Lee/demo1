package com.app.auth.model;

import java.io.Serializable;

public class UserToken implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String id;
    private String email;
    private String issued_at;
    private String expires_at;
    
    public UserToken() {
        super();
    }

    public UserToken(String id, String email, String issued_at, String expires_at) {
        this.id = id;
        this.email = email;
        this.issued_at = issued_at;
        this.expires_at = expires_at;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIssued_at() {
        return issued_at;
    }
    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }
    public String getExpires_at() {
        return expires_at;
    }
    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
    
    
    
    
}
