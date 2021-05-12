package com.app.cust.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.app.base.config.AbstractMapper;

/**
 * 
 * @Description 
 * @author 이호석
 * @since 2021.05.04
 * @version 1.0
 *   
 *  수정일       수정자      수정내용
 *  ----------   --------    ---------------------------
 *  2021.05.04   이호석      최초 생성
 *
 *
 */

@Repository("cust01DAO")
public class Cust01DAO extends AbstractMapper {

	
	/**
	 * 회원정보 - 회원번호
     * Customer info inquiry
     * 
	 * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByCustNo(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByCustNo", inMap);
    }
	
	/**
     * 회원정보 - 전화번호
     * Customer info inquiry
     * 
     * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByTelNo(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByTelNo", inMap);
    }
	
	/**
     * 회원정보 - 이메일
     * Customer info inquiry
     * 
     * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByEmail(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByEmail", inMap);
    }

	/**
     * 회원목록
     * Customer info inquiry List
     * 
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getCustInfoList(Map<String, Object> inMap) {
        return selectList("app.cust.Cust01.getCustInfoList", inMap);
    }

    /**
     * 회원목록시 조회되는 총 건수
     * Customer info inquiry List Count
     * 
     * @return List<Map<String, Object>>
     */
    public int countCustInfoList(Map<String, Object> inMap) {
        return selectOne("app.cust.Cust01.countCustInfoList", inMap);
    }

    /**
     * 회원가입시 이메일 중복 검사
     * 
     * @return List<Map<String, Object>>
     */
    public int countCustInfoEmail(Map<String, Object> inMap) {
        return selectOne("app.cust.Cust01.countCustInfoEmail", inMap);
    }
	
  
}
