package com.jeeshop.domain;

import java.util.Date;

public class AdminVO {

	/*
	ADMIN_ID			VARCHAR2(15)					PRIMARY KEY,
	ADMIN_PW			VARCHAR2(30)					NOT NULL,
	ADMIN_NAME			VARCHAR2(15)					NOT NULL,
	ADMIN_DATE	        DATE DEFAULT	SYSDATE			NOT NULL
	 */
	private String 	admin_id;
	private String 	admin_pw;
	private String 	admin_name;
	private Date	admin_date;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public Date getAdmin_date() {
		return admin_date;
	}
	public void setAdmin_date(Date admin_date) {
		this.admin_date = admin_date;
	}
	
	@Override
	public String toString() {
		return "AdminVO [admin_id=" + admin_id + ", admin_pw=" + admin_pw + ", admin_name=" + admin_name
				+ ", admin_date=" + admin_date + "]";
	}
}
