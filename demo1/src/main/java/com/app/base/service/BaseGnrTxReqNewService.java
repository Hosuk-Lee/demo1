package com.app.base.service;


/**
 *
 * @Description 공통채번
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
public interface BaseGnrTxReqNewService {
    
    public String getKeyId_Tx_Requires_New(String keyCd, String keyTxt, String tblNm, int pad) throws Exception;

}
