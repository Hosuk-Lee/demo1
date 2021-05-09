package com.app.setl.service;

import java.util.Map;

public interface Setl01Service  {
    
    /* 단일 회원의 주문 목록 조회 */
    public Map<String, Object> getCustOrdList(Map<String, Object> inMap) throws Exception;
    
}
