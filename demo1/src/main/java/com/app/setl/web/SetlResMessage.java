package com.app.setl.web;

public class SetlResMessage {
    
    public static final String custOrdList_200 = "정상 \n"
            + "- JSON DATA \n" +
            "  \"total_count\": 100, 조회되는 총 건수입니다. \n" + 
            "  \"elements\": [ 요청한 페이지당 건수만큼 출력됩니다. \n" + 
            "    {\n" + 
            "      \"rn\": \"11\", 조회순번 \n" + 
            "      \"ordNo\": \"210508000011\", 주문번호 \n" + 
            "      \"selCustNo\": \"ZZ00000001\", 판매 고객번호 \n" + 
            "      \"buyCustNo\": \"ZZ00000002\", 구매 고객번호 \n" + 
            "      \"pdtNm\": \"38emoji384\", 상품이름\n" + 
            "      \"stlDt\": \"2021-02-08\", 결제일자(표준시)\n" + 
            "      \"stlTm\": \"17:04:46\", 결제시간(표준시)\n" + 
            "      \"kstStlDt\": \"2021-02-09\", 결제일자(한국시) \n" + 
            "      \"kstStlTm\": \"02:04:46\" 결제시간(한국시) \n" + 
            "    },\n" + 
            "    {\n" + 
            "      \"rn\": \"12\",\n" + 
            "      \"ordNo\": \"210508000012\",\n" + 
            "      \"selCustNo\": \"ZZ00000001\",\n" + 
            "      \"buyCustNo\": \"ZZ00000002\",\n" + 
            "      \"pdtNm\": \"384A7B6emoji384A\",\n" + 
            "      \"stlDt\": \"2021-02-09\",\n" + 
            "      \"stlTm\": \"17:04:46\",\n" + 
            "      \"kstStlDt\": \"2021-02-10\",\n" + 
            "      \"kstStlTm\": \"02:04:46\"\n" + 
            "    },\n" + 
            "    {...}\n" + 
            "  ],\n" + 
            "  \"next_url\": \"/api/setlSvc/custOrdList?page=3&limit=10&order=asc\", 다음페이지 URL 주소입니다. page를 1씩 증가시켜도 됩니다. 다음페이지가 없으면 표시되지 않습니다.\n" + 
            "  \"previous_url\": \"/api/setlSvc/custOrdList?page=1&limit=10&order=asc\" 이전페이지 URL 주소입니다. page를 1씩 감소시켜도 됩니다. 이전페이지가 없으면 표시되지 않습니다.\n" + 
            "}"; 
}
