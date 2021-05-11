package com.app.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "회원가입 정보")
public class UserSignUp  {
    
    @ApiModelProperty(value = "이름", required = true, example = "한글, 영문 대소문자 허용")
    private String nm           = "";
    @ApiModelProperty(value = "별명", required = true, example = "영문 소문자만 허용")
    private String alias        = "";
    @ApiModelProperty(value = "비밀번호", required = true, example = "영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함")
    private String password     = "";
    @ApiModelProperty(value = "전화번호", required = true, example = "숫자 ex. 01099426828")
    private String phone_number = "";
    @ApiModelProperty(value = "이메일", required = true, example = "이메일 형식")
    private String email        = "";
    @ApiModelProperty(value = "성멸", required = false, example = "성별 (남자:M, 여자W)")
    private String sex          = "";
    
    public String getNm() {
        return nm;
    }
    public void setNm(String nm) {
        this.nm = nm;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
    
}
