package com.app.cust.web;

public class CustResMessage {

    //
    public static final String custInfo_200 = "정상 \n"
            + "- JSON DATA \n" +
            "{\n" + 
            "  \"custNo\": \"ZZ00000001\", 회원가입 시 부여되는 고객번호\n" + 
            "  \"custGb\": \"개인\", 고객구분(개인,기업) \n " + 
            "  \"nm\": \"NAME1\",  이름\n" + 
            "  \"alias\": \"ALIAS1\",  별명\n" + 
            "  \"telNo\": \"01099990001\",  전화번호\n" + 
            "  \"email\": \"MAIL0001@idus.com\",  메일\n" + 
            "  \"sex\": \"여성\"  성별\n" + 
            "}";
    
    //
    public static final String custInfoList_200 = "정상 \n"
            + "- JSON DATA \n" +
            "{\n" + 
            "  \"total_count\": 1, 조회되는 총 건수입니다. \n" +
            "  \"elements\": [ 요청한 페이지당 건수만큼 출력됩니다. \n" + 
            "    {\n" + 
            "      \"rn\": \"1\", 조회순번 \n" + 
            "      \"custNo\": \"ZZ00000001\", 고객번호 \n" + 
            "      \"custGb\": \"개인\", 고객구분(개인,기업) \n" + 
            "      \"nm\": \"NAME1\", 이름 \n" + 
            "      \"alias\": \"ALIAS1\", 별명 \n" + 
            "      \"telNo\": \"01099990001\", 전화번호 \n" + 
            "      \"email\": \"MAIL0001@idus.com\", 메일 \n" + 
            "      \"sex\": \"여성\" 성별 \n" + 
            "    }\n" + 
            "  ],\n" + 
            "  \"next_url\": \"/api/setlSvc/custOrdList?page=3&limit=10&order=asc\", 다음페이지 URL 주소입니다. page를 1씩 증가시켜도 됩니다. 다음페이지가 없으면 표시되지 않습니다.\n" + 
            "  \"previous_url\": \"/api/setlSvc/custOrdList?page=1&limit=10&order=asc\" 이전페이지 URL 주소입니다. page를 1씩 감소시켜도 됩니다. 이전페이지가 없으면 표시되지 않습니다.\n" + 
            "}";
}
