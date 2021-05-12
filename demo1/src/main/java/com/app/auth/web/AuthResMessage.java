package com.app.auth.web;

public class AuthResMessage {

    //
    public static final String auth_200 = "정상 (Http) 데이터를 꼭 확인하세요. \n"
            + "- JSON DATA \n" +
            " 서비스 정상 응답" +
            "{\n" + 
            "  \"result\": \"success\", \n" + 
            "  \"message\": \"\", \n " + 
            "}" +
            " 서비스 응답 오류" +
            "{\n" + 
            "  \"result\": \"error\", \n" + 
            "  \"message\": \"오류 메시지 정보.\", \n " + 
            "}";
    

}
