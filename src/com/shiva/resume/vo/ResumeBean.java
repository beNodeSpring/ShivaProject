package com.shiva.resume.vo;

import java.sql.Date;
/*
 	데이터 빈(DataBean) 클래스 작성
 	게시판에서 사용되는 정보들을 데이터 빈이라는 하나의 객체에 저장하게 되고
 	이 객체를 전달하면 각 정보를 하나씩 전달할 필요가 없으며
 	한꺼번에 모든 정보가 전달됩니다.
 	이런 클래스를 DTO(Data Transfer Object), (Value Object)라고 합니다.
 	DB에서 만들었던 컬럼명과 동일하게 프로퍼티들을 생성합니다.
*/
public class ResumeBean {
	private String RESUME_ID;
	private Date   RESUME_DATE;
	private String TODAYFEELING;
	private String PROJECT1;
	private String PROJECT2;
	private String PROJECT3;
	private String PROJECT4;
	private String PROJECT5;
	private String PROJECT6;
	private String PROJECT7;
	private String PROJECT8;
	private String PROJECT9;
	private String DURATION1;
	private String DURATION2;
	private String DURATION3;
	private String DURATION4;
	private String DURATION5;
	private String DURATION6;
	private String DURATION7;
	private String DURATION8;
	private String DURATION9;
	private String TEXT1;
	private String TEXT2;
	private String TEXT3;
	private String TEXT4;
	private String TEXT5;
	private String TEXT6;
	private String TEXT7;
	private String TEXT8;
	private String TEXT9;
	private String JAVAVAL;
	private String PYTHONVAL;
	private String CVAL;
	private String RUBYVAL;
	private String JAVASCRIPTVAL;
	private String CSHAPVAL;
	private String PHPVAL;
	private String OBJECTIVECVAL;
	private String SQLVAL;
	private String CPLUSVAL;
	private String PIC;
	private String VISITORPIC;
	
	public String getVISITORPIC() {
		return VISITORPIC;
	}
	public void setVISITORPIC(String vISITORPIC) {
		VISITORPIC = vISITORPIC;
	}
	public String getVISITOR_ID() {
		return VISITOR_ID;
	}
	public void setVISITOR_ID(String vISITOR_ID) {
		VISITOR_ID = vISITOR_ID;
	}
	private String VISITOR_ID;
	
	public String getRESUME_ID() {
		return RESUME_ID;
	}
	public void setRESUME_ID(String rESUME_ID) {
		RESUME_ID = rESUME_ID;
	}
	public String getTODAYFEELING() {
		return TODAYFEELING;
	}
	public void setTODAYFEELING(String tODAYFEELING) {
		TODAYFEELING = tODAYFEELING;
	}
	public String getPROJECT1() {
		return PROJECT1;
	}
	public void setPROJECT1(String pROJECT1) {
		PROJECT1 = pROJECT1;
	}
	public String getPROJECT2() {
		return PROJECT2;
	}
	public void setPROJECT2(String pROJECT2) {
		PROJECT2 = pROJECT2;
	}
	public String getPROJECT3() {
		return PROJECT3;
	}
	public void setPROJECT3(String pROJECT3) {
		PROJECT3 = pROJECT3;
	}
	public String getPROJECT4() {
		return PROJECT4;
	}
	public void setPROJECT4(String pROJECT4) {
		PROJECT4 = pROJECT4;
	}
	public String getPROJECT5() {
		return PROJECT5;
	}
	public void setPROJECT5(String pROJECT5) {
		PROJECT5 = pROJECT5;
	}
	public String getPROJECT6() {
		return PROJECT6;
	}
	public void setPROJECT6(String pROJECT6) {
		PROJECT6 = pROJECT6;
	}
	public String getPROJECT7() {
		return PROJECT7;
	}
	public void setPROJECT7(String pROJECT7) {
		PROJECT7 = pROJECT7;
	}
	public String getPROJECT8() {
		return PROJECT8;
	}
	public void setPROJECT8(String pROJECT8) {
		PROJECT8 = pROJECT8;
	}
	public String getPROJECT9() {
		return PROJECT9;
	}
	public void setPROJECT9(String pROJECT9) {
		PROJECT9 = pROJECT9;
	}
	public String getDURATION1() {
		return DURATION1;
	}
	public void setDURATION1(String dURATION1) {
		DURATION1 = dURATION1;
	}
	public String getDURATION2() {
		return DURATION2;
	}
	public void setDURATION2(String dURATION2) {
		DURATION2 = dURATION2;
	}
	public String getDURATION3() {
		return DURATION3;
	}
	public void setDURATION3(String dURATION3) {
		DURATION3 = dURATION3;
	}
	public String getDURATION4() {
		return DURATION4;
	}
	public void setDURATION4(String dURATION4) {
		DURATION4 = dURATION4;
	}
	public String getDURATION5() {
		return DURATION5;
	}
	public void setDURATION5(String dURATION5) {
		DURATION5 = dURATION5;
	}
	public String getDURATION6() {
		return DURATION6;
	}
	public void setDURATION6(String dURATION6) {
		DURATION6 = dURATION6;
	}
	public String getDURATION7() {
		return DURATION7;
	}
	public void setDURATION7(String dURATION7) {
		DURATION7 = dURATION7;
	}
	public String getDURATION8() {
		return DURATION8;
	}
	public void setDURATION8(String dURATION8) {
		DURATION8 = dURATION8;
	}
	public String getDURATION9() {
		return DURATION9;
	}
	public void setDURATION9(String dURATION9) {
		DURATION9 = dURATION9;
	}
	public String getTEXT1() {
		return TEXT1;
	}
	public void setTEXT1(String tEXT1) {
		TEXT1 = tEXT1;
	}
	public String getTEXT2() {
		return TEXT2;
	}
	public void setTEXT2(String tEXT2) {
		TEXT2 = tEXT2;
	}
	public String getTEXT3() {
		return TEXT3;
	}
	public void setTEXT3(String tEXT3) {
		TEXT3 = tEXT3;
	}
	public String getTEXT4() {
		return TEXT4;
	}
	public void setTEXT4(String tEXT4) {
		TEXT4 = tEXT4;
	}
	public String getTEXT5() {
		return TEXT5;
	}
	public void setTEXT5(String tEXT5) {
		TEXT5 = tEXT5;
	}
	public String getTEXT6() {
		return TEXT6;
	}
	public void setTEXT6(String tEXT6) {
		TEXT6 = tEXT6;
	}
	public String getTEXT7() {
		return TEXT7;
	}
	public void setTEXT7(String tEXT7) {
		TEXT7 = tEXT7;
	}
	public String getTEXT8() {
		return TEXT8;
	}
	public void setTEXT8(String tEXT8) {
		TEXT8 = tEXT8;
	}
	public String getTEXT9() {
		return TEXT9;
	}
	public void setTEXT9(String tEXT9) {
		TEXT9 = tEXT9;
	}
	public Date getRESUME_DATE() {
		return RESUME_DATE;
	}
	public void setRESUME_DATE(Date rESUME_DATE) {
		RESUME_DATE = rESUME_DATE;
	}
	public String getJAVAVAL() {
		return JAVAVAL;
	}
	public void setJAVAVAL(String jAVAVAL) {
		JAVAVAL = jAVAVAL;
	}
	public String getPYTHONVAL() {
		return PYTHONVAL;
	}
	public void setPYTHONVAL(String pYTHONVAL) {
		PYTHONVAL = pYTHONVAL;
	}
	public String getCVAL() {
		return CVAL;
	}
	public void setCVAL(String cVAL) {
		CVAL = cVAL;
	}
	public String getRUBYVAL() {
		return RUBYVAL;
	}
	public void setRUBYVAL(String rUBYVAL) {
		RUBYVAL = rUBYVAL;
	}
	public String getJAVASCRIPTVAL() {
		return JAVASCRIPTVAL;
	}
	public void setJAVASCRIPTVAL(String jAVASCRIPTVAL) {
		JAVASCRIPTVAL = jAVASCRIPTVAL;
	}
	public String getCSHAPVAL() {
		return CSHAPVAL;
	}
	public void setCSHAPVAL(String cSHAPVAL) {
		CSHAPVAL = cSHAPVAL;
	}
	public String getPHPVAL() {
		return PHPVAL;
	}
	public void setPHPVAL(String pHPVAL) {
		PHPVAL = pHPVAL;
	}
	public String getOBJECTIVECVAL() {
		return OBJECTIVECVAL;
	}
	public void setOBJECTIVECVAL(String oBJECTIVECVAL) {
		OBJECTIVECVAL = oBJECTIVECVAL;
	}
	public String getSQLVAL() {
		return SQLVAL;
	}
	public void setSQLVAL(String sQLVAL) {
		SQLVAL = sQLVAL;
	}
	public String getCPLUSVAL() {
		return CPLUSVAL;
	}
	public void setCPLUSVAL(String cPLUSVAL) {
		CPLUSVAL = cPLUSVAL;
	}
	public String getPIC() {
		return PIC;
	}
	public void setPIC(String pIC) {
		PIC = pIC;
	}
}
