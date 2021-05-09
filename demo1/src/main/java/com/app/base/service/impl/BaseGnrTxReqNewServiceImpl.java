package com.app.base.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.app.base.service.BaseGnrTxReqNewService;
import com.app.base.util.StringUtil;
import com.app.table.base.dao.TbBaseKeyMakeDAO;
import com.app.table.base.model.TbBaseKeyMakeVO;



/**
 * 채번함수
 * @Description 채번
 * @author 이호석
 * @since 2021.05.06
 * @version 1.0
 *
 *  수정일         수정자               수정내용
 *  ----------   --------    ---------------------------
 *  2021.05.06     이호석               최초 생성
 *
 *
 */
@Service("baseGnrTxReqNewService")
public class BaseGnrTxReqNewServiceImpl implements BaseGnrTxReqNewService {

	@Resource(name = "tbBaseKeyMakeDAO")
	private TbBaseKeyMakeDAO tbBaseKeyMakeDAO;

    
    /**
     * 채번함수-return String
     * @param keyCd
     * @param keyTxt
     * @param tblNm
     * @param pad
     * @return
     * @throws Exception
     */
    public synchronized String getKeyId_Tx_Requires_New(String keyCd, String keyTxt, String tblNm, int pad) throws Exception {
        String key = null;
        StringBuffer sb = new StringBuffer();
        long serNo = 0L;
        try {
            serNo = this.getSerNo(keyCd, keyTxt, tblNm);
        } catch (DuplicateKeyException ex) {
            serNo = this.getSerNo(keyCd, keyTxt, tblNm);
        }
        BigDecimal bdSerNo = new BigDecimal(serNo);
        sb.append(keyTxt);
        sb.append(StringUtil.leftPad(bdSerNo.toString(), pad, '0'));
        key = sb.toString();
        return key;
    }    
    
	/**
	 * 공통채번
	 * @param keyCd
	 * @param keyTxt
	 * @param tblNm
	 * @return rtnSerNo
	 * @throws Exception
	 */
	private long getSerNo(String keyCd, String keyTxt, String tblNm) throws Exception {
		long rtnSerNo = 1L;
		if (StringUtil.isEmpty(keyCd))
			throw new RuntimeException("KEY ID 누락" );
		if (StringUtil.isEmpty(keyTxt))
		    throw new RuntimeException("KEY 구분값 누락" );

//		if (StringUtil.isEmpty(tblNm))
//			throw runtimeException("테이블명" );

		TbBaseKeyMakeVO inVo = new TbBaseKeyMakeVO();
		inVo.setKeyCd(keyCd);
		inVo.setKeyTxt(keyTxt);
		TbBaseKeyMakeVO rtnVo = this.tbBaseKeyMakeDAO.doSelectForUpdate(inVo);
		if (rtnVo != null) {
			rtnSerNo = rtnVo.getSerNo() + 1L;
			rtnVo.setSerNo(rtnSerNo);
			rtnVo.setTblNm(tblNm);
			this.tbBaseKeyMakeDAO.doUpdate(rtnVo);
		} else {
			rtnSerNo = 1L;
			inVo.setSerNo(rtnSerNo);
			inVo.setTblNm(tblNm);
			this.tbBaseKeyMakeDAO.doInsert(inVo);
		}
		return rtnSerNo;
	}


}
