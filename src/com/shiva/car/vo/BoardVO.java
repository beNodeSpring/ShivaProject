package com.shiva.car.vo;

import java.sql.Date;


public class BoardVO {
	private int BOARD_NO;     
	private String BOARD_NAME;  
	private String BOARD_SUBJECT;
	private String BOARD_CONTENT; 
	private Date   BOARD_DATE;
	private int    BOARD_COUNT;
	public int getBOARD_NO() {
		return BOARD_NO;
	}
	public void setBOARD_NO(int bOARD_NO) {
		BOARD_NO = bOARD_NO;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(Date bOARD_DATE) {
		BOARD_DATE = bOARD_DATE;
	}
	public int getBOARD_COUNT() {
		return BOARD_COUNT;
	}
	public void setBOARD_COUNT(int bOARD_COUNT) {
		BOARD_COUNT = bOARD_COUNT;
	}     
	
	
	
	
	
	
}
