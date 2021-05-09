package com.app.table.cust.dao;

import org.springframework.stereotype.Repository;

import com.app.base.config.AbstractMapper;
import com.app.table.cust.model.TbCustBaseVO;




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

@Repository("tbCustBaseDAO")
public class TbCustBaseDAO extends AbstractMapper {

	
	/**
     * TB_CUST_BASE(고객기본) 생성
     *
     * @param TbCustBaseVO
     * @return void
     * @throws Exception
     */
    public void doInsert(TbCustBaseVO inVo) throws Exception {
    	insert("app.table.cust.tbCustBase.doInsert", inVo);
    }

    /**
     * TB_CUST_BASE(고객기본) 수정
     *
     * @param TbCustBaseVO
     * @return update 적용결과 count
     * @throws Exception
     */
    public int doUpdate(TbCustBaseVO inVo) throws Exception {
        return update("app.table.cust.tbCustBase.doUpdate", inVo);
    }

    /**
     * TB_CUST_BASE(고객기본) 삭제
     *
     * @param TbCustBaseVO
     * @return delete 적용결과 count
     * @throws Exception
     */
    public int doDelete(TbCustBaseVO inVo) throws Exception {
        return delete("app.table.cust.tbCustBase.doDelete", inVo);
    }

    /**
     * TB_CUST_BASE(고객기본) 조회
     *
     * @param TbCustBaseVO
     * @return TbCustBaseVO
     * @throws Exception
     */
    public TbCustBaseVO doSelect(TbCustBaseVO inVo) throws Exception {
        return (TbCustBaseVO)selectOne("app.table.cust.tbCustBase.doSelect", inVo);
    }

    /**
     * TB_CUST_BASE(고객기본) 조회 FOR UPDATE NO WAIT
     *
     * @param TbCustBaseVO
     * @return TbCustBaseVO
     * @throws Exception
     */
    public TbCustBaseVO doSelectForUpdate(TbCustBaseVO inVo) throws Exception {

        return (TbCustBaseVO)selectOne("app.table.cust.tbCustBase.doSelectForUpdate", inVo);
    }

}
