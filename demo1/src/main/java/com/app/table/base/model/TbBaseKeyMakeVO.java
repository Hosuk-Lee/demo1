package com.app.table.base.model;

public class TbBaseKeyMakeVO {

    private String keyCd  = ""; // 1 Key Code
    private String keyTxt   = ""; // 2 Key Text
    private long   serNo    = 0L; // 3 일련번호
    private String tblNm    = ""; // 4 테이블이름
    
    public String getKeyCd() {
        return keyCd;
    }
    public void setKeyCd(String keyCd) {
        this.keyCd = keyCd;
    }
    public String getKeyTxt() {
        return keyTxt;
    }
    public void setKeyTxt(String keyTxt) {
        this.keyTxt = keyTxt;
    }
    public long getSerNo() {
        return serNo;
    }
    public void setSerNo(long serNo) {
        this.serNo = serNo;
    }
    public String getTblNm() {
        return tblNm;
    }
    public void setTblNm(String tblNm) {
        this.tblNm = tblNm;
    }
    
        
    
}
