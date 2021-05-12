package com.app.base.config;

/**
 *  Service 단에서 일어난 오류(로직상 필요한 Error)를 
 *  Controller에서 Catch 하기 위해 
 *  */
public class DemoServiceException extends RuntimeException {
    private static final long serialVersionUID = 1;

    
    private String message;
    private String code;
    
    public DemoServiceException(String message) {
        super();
        this.message = message;
        this.code = "";
    }

    
    public DemoServiceException(String message, String code) {
        super();
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    



}
