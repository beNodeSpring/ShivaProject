package com.shiva.license.vo;

import java.sql.Date;

public class LicenseCalendar_ConVO {
	private Date ITC_DATE ;
	private String ITC_NAME ;
	private String ITC_LINK ;
	private String ITC_STRINGDATE ;
	
	public String getITC_LINK() {
		return ITC_LINK;
	}
	public void setITC_LINK(String iTC_LINK) {
		ITC_LINK = iTC_LINK;
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
