package com.app.table.setl.dao;

import org.springframework.stereotype.Repository;

import com.app.base.config.AbstractMapper;
import com.app.table.setl.model.TbSetlBaseVO;




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

@Repository("tbSetlBaseDAO")
public class TbSetlBaseDAO extends AbstractMapper {

    
    /**
     * TB_SETL_BASE(결제기본) 생성
     *
     * @param TbSetlBaseVO
     * @return void
     * @throws Exception
     */
    public void doInsert(TbSetlBaseVO inVo) throws Exception {
        insert("app.table.setl.tbSetlBase.doInsert", inVo);
    }

    /**
     * TB_SETL_BASE(결제기본) 수정
     *
     * @param TbSetlBaseVO
     * @return update 적용결과 count
     * @throws Exception
     */
    public int doUpdate(TbSetlBaseVO inVo) throws Exception {
        return update("app.table.setl.tbSetlBase.doUpdate", inVo);
    }

    /**
     * TB_SETL_BASE(결제기본) 삭제
     *
     * @param TbSetlBaseVO
     * @return delete 적용결과 count
     * @throws Exception
     */
    public int doDelete(TbSetlBaseVO inVo) throws Exception {
        return delete("app.table.setl.tbSetlBase.doDelete", inVo);
    }

    /**
     * TB_SETL_BASE(결제기본) 조회
     *
     * @param TbSetlBaseVO
     * @return TbSetlBaseVO
     * @throws Exception
     */
    public TbSetlBaseVO doSelect(TbSetlBaseVO inVo) throws Exception {
        return (TbSetlBaseVO)selectOne("app.table.setl.tbSetlBase.doSelect", inVo);
    }

    /**
     * TB_SETL_BASE(결제기본) 조회 FOR UPDATE NO WAIT
     *
     * @param TbSetlBaseVO
     * @return TbSetlBaseVO
     * @throws Exception
     */
    public TbSetlBaseVO doSelectForUpdate(TbSetlBaseVO inVo) throws Exception {

        return (TbSetlBaseVO)selectOne("app.table.setl.tbSetlBase.doSelectForUpdate", inVo);
    }

}
