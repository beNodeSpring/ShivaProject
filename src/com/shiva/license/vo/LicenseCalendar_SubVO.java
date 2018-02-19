package com.shiva.license.vo;

import java.sql.Date;

public class LicenseCalendar_SubVO {
	private Date ITC_DATE ;
	private String ITC_NAME ;
	private int ITC_TYPE ;
	private String ITC_STRINGDATE ;
	public int getITC_TYPE() {
		return ITC_TYPE;
	}
	public void setITC_TYPE(int iTC_TYPE) {
		ITC_TYPE = iTC_TYPE;
	}
	public Date getITC_DATE() {
		return ITC_DATE;
	}
	public void setITC_DATE(Date iTC_DATE) {
		ITC_DATE = iTC_DATE;
	}
	public String getITC_NAME() {
		return ITC_NAME;
	}
	public void setITC_NAME(String iTC_NAME) {
		ITC_NAME = iTC_NAME;
	}
	public String getITC_STRINGDATE() {
		return ITC_STRINGDATE;
	}
	public void setITC_STRINGDATE(String iTC_STRINGDATE) {
		ITC_STRINGDATE = iTC_STRINGDATE;
	}

}
