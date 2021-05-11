package com.app.cust.service;

import java.util.Map;

public interface Cust01Service  {
    
	/* 회원가입 */
	public Map<String, Object> signUp(Map<String, Object> inMap) throws Exception;
	
	/* 로그인 */
	public Map<String, Object> signIn(Map<String, Object> inMap) throws Exception;

	/* 로그아웃 */
	public Map<String, Object> logout(Map<String, Object> inMap) throws Exception;
	
	/* 단일 회원의 주문 목록 조회 */
    public Map<String, Object> getCustInfo(Map<String, Object> inMap) throws Exception;

    /* - 여러 회원 목록 조회 :
    - 페이지네이션으로 일정 단위로 조회합니다.
    - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
    - 각 회원의 마지막 주문 정보
    */
    public Map<String, Object> custInfoList(Map<String, Object> inMap);


    
}
