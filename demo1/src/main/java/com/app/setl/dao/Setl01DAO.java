package com.app.setl.dao;

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

@Repository("setl01DAO")
public class Setl01DAO extends AbstractMapper {

    
    /**
     * Only One String Sample
     * 
     * @return String
     * 
     */
    public String selectDual(Map<String, Object> inMap) throws Exception {
        return selectOne("app.setl.Setl01.selectDual", inMap);
    }
    
    /**
     * 
     * Multi rows & multi columns sample
     * 
     * @return List<Map<String, Object>>
     * 
     */
    public List<Map<String, Object>> listDual(Map<String, Object> inMap) throws Exception {
//        return selectOne("app.setl.Setl01.selectDual", inMap);
        return selectList("app.setl.Setl01.listDual", inMap);
    }
    
    
    /**
     * 
     * 단일 회원의 주문 목록 조회
     * @param  Map<"buyCustNo", String> buyCustNo
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public List<Map<String, Object>> getCustOrdList(Map<String, Object> inMap) throws Exception {
//        return selectOne("app.setl.Setl01.selectDual", inMap);
        return selectList("app.setl.Setl01.getCustOrdList", inMap);
    }

    /**
     * 
     * 단일 회원의 주문 목록 건수
     * @param  Map<"buyCustNo", String> buyCustNo
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    public int countCustOrdList(Map<String, Object> inMap) {
        return selectOne("app.setl.Setl01.countCustOrdList", inMap);
    }

  
}
