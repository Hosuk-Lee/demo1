package com.app.table.base.dao;

import org.springframework.stereotype.Repository;

import com.app.base.config.AbstractMapper;
import com.app.table.base.model.TbBaseKeyMakeVO;

/**
 * 
 * @Description 
 * @author 이호석
 * @since 2021.05.06
 * @version 1.0
 *   
 *  수정일       수정자      수정내용
 *  ----------   --------    ---------------------------
 *  2021.05.06   이호석      최초 생성
 *
 *
 */

@Repository("tbBaseKeyMakeDAO")
public class TbBaseKeyMakeDAO extends AbstractMapper {

	
	/**
     * TB_BASE_KEY_MAKE(기본KEY생성) 생성
     *
     * @param TbBaseKeyMakeVO
     * @return void
     * @throws Exception
     */
    public void doInsert(TbBaseKeyMakeVO inVo) throws Exception {
    	insert("app.table.base.tbBaseKeyMake.doInsert", inVo);
    }

    /**
     * TB_BASE_KEY_MAKE(기본KEY생성) 수정
     *
     * @param TbBaseKeyMakeVO
     * @return update 적용결과 count
     * @throws Exception
     */
    public int doUpdate(TbBaseKeyMakeVO inVo) throws Exception {
        return update("app.table.base.tbBaseKeyMake.doUpdate", inVo);
    }

    /**
     * TB_BASE_KEY_MAKE(기본KEY생성) 삭제
     *
     * @param TbBaseKeyMakeVO
     * @return delete 적용결과 count
     * @throws Exception
     */
    public int doDelete(TbBaseKeyMakeVO inVo) throws Exception {
        return delete("app.table.base.tbBaseKeyMake.doDelete", inVo);
    }

    /**
     * TB_BASE_KEY_MAKE(기본KEY생성) 조회
     *
     * @param TbBaseKeyMakeVO
     * @return TbBaseKeyMakeVO
     * @throws Exception
     */
    public TbBaseKeyMakeVO doSelect(TbBaseKeyMakeVO inVo) throws Exception {
        return (TbBaseKeyMakeVO)selectOne("app.table.base.tbBaseKeyMake.doSelect", inVo);
    }

    /**
     * TB_BASE_KEY_MAKE(기본KEY생성) 조회 FOR UPDATE NO WAIT
     *
     * @param TbBaseKeyMakeVO
     * @return TbBaseKeyMakeVO
     * @throws Exception
     */
    public TbBaseKeyMakeVO doSelectForUpdate(TbBaseKeyMakeVO inVo) throws Exception {

        return (TbBaseKeyMakeVO)selectOne("app.table.base.tbBaseKeyMake.doSelectForUpdate", inVo);
    }

  
}
