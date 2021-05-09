package com.app.table.cust.model;

public class TbCustBaseVO {

	private String custNo = ""; // 1
	private String nm     = ""; // 2 mandantory field
	private String alias  = ""; // 3 mandantory field
	private String pwd    = ""; // 4 mandantory field
	private String telNo  = ""; // 5 mandantory field
	private String email  = ""; // 6 mandantory field
    private String sex    = ""; // 7 mandantory field
    private String custGb = ""; // 8
    
    
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCustGb() {
		return custGb;
	}
	public void setCustGb(String custGb) {
		this.custGb = custGb;
	}
    
    
}
