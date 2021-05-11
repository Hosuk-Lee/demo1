package com.app.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "로그아웃")
public class UserLogout {
    
    @ApiModelProperty(value = "이메일", required = true, example = "MAIL0001@idus.com")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
