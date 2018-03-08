package com.shiva.used.VO;

import java.sql.Date;

public class used_sale_replyVO {
	private int NUM_S;
	private String NAME_S;
	private String CONTENT_S;
	public int getNUM_S() {
		return NUM_S;
	}
	public void setNUM_S(int nUM_S) {
		NUM_S = nUM_S;
	}
	public String getNAME_S() {
		return NAME_S;
	}
	public void setNAME_S(String nAME_S) {
		NAME_S = nAME_S;
	}
	public String getCONTENT_S() {
		return CONTENT_S;
	}
	public void setCONTENT_S(String cONTENT_S) {
		CONTENT_S = cONTENT_S;
	}
	public Date getDATE_S() {
		return DATE_S;
	}
	public void setDATE_S(Date dATE_S) {
		DATE_S = dATE_S;
	}
	private Date DATE_S;
}
