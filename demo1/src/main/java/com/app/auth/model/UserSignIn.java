package com.app.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "로그인 정보")
public class UserSignIn {

    @ApiModelProperty(value = "이메일", required = true, example = "MAIL0001@idus.com")
    private String email;
    @ApiModelProperty(value = "비밀번호", required = true, example = "iDus1234!@#$")
    private String pwd;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
}
