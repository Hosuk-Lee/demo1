package com.app.table.setl.model;

import java.util.Date;

public class TbSetlBaseVO {

	private String ordNo     = "";
	private String selCustNo = "";
	private String buyCustNo = "";
	private String pdtNm     = "";
	private Date   stlDate   = null;
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getSelCustNo() {
		return selCustNo;
	}
	public void setSelCustNo(String selCustNo) {
		this.selCustNo = selCustNo;
	}
	public String getBuyCustNo() {
		return buyCustNo;
	}
	public void setBuyCustNo(String buyCustNo) {
		this.buyCustNo = buyCustNo;
	}
	public String getPdtNm() {
		return pdtNm;
	}
	public void setPdtNm(String pdtNm) {
		this.pdtNm = pdtNm;
	}
	public Date getStlDate() {
        return stlDate;
    }
    public void setStlDate(Date stlDate) {
        this.stlDate = stlDate;
    }
	
}
