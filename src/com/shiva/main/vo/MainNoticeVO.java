package com.shiva.main.vo;

import java.sql.Date;

// 메인 공지사항 게시판
public class MainNoticeVO {
	private int bdNum;
	private String bdId;
	private String bdSubject;
	private String bdContent;
	private String bdFile;
	private String bdHits;
	private Date bdDate;
	
	public int getBdNum() {
		return bdNum;
	}
	public void setBdNum(int i) {
		this.bdNum = i;
	}
	public String getBdId() {
		return bdId;
	}
	public void setBdId(String bdId) {
		this.bdId = bdId;
	}
	public String getBdSubject() {
		return bdSubject;
	}
	public void setBdSubject(String bdSubject) {
		this.bdSubject = bdSubject;
	}
	public String getBdContent() {
		return bdContent;
	}
	public void setBdContent(String bdContent) {
		this.bdContent = bdContent;
	}
	public String getBdFile() {
		return bdFile;
	}
	public void setBdFile(String bdFile) {
		this.bdFile = bdFile;
	}
	public String getBdHits() {
		return bdHits;
	}
	public void setBdHits(String bdHits) {
		this.bdHits = bdHits;
	}
	public Date getBdDate() {
		return bdDate;
	}
	public void setBdDate(Date bdDate) {
		this.bdDate = bdDate;
	}
}
