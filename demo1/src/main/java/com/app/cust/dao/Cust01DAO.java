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
	 * 고객정보 - 고객번호
     * Customer info inquiry
     * 
	 * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByCustNo(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByCustNo", inMap);
    }
	
	/**
     * 고객정보 - 전화번호
     * Customer info inquiry
     * 
     * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByTelNo(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByTelNo", inMap);
    }
	
	/**
     * 고객정보 - 이메일
     * Customer info inquiry
     * 
     * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> selectCustInfoByEmail(Map<String, Object> inMap) throws Exception {
		return selectList("app.cust.Cust01.selectCustInfoByEmail", inMap);
    }

    public List<Map<String, Object>> getCustInfoList(Map<String, Object> inMap) {
        return selectList("app.cust.Cust01.getCustInfoList", inMap);
    }

    public int countCustInfoList(Map<String, Object> inMap) {
        return selectOne("app.cust.Cust01.countCustInfoList", inMap);
    }
	
  
}
