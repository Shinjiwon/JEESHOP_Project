package com.jeeshop.domain;

import java.util.Date;

public class MemberVO {

	/*
	MB_ID               VARCHAR2(15)  PRIMARY KEY,
    MB_NAME             VARCHAR2(30)              NOT NULL,
    MB_PW               VARCHAR2(60)              NOT NULL,
    MB_POSTCODE         CHAR(5)                   NOT NULL,
    MB_ADDR             VARCHAR2(50)              NOT NULL,
    MB_DEADDR           VARCHAR2(50)              NOT NULL,
    MB_PHONE            VARCHAR2(15)              NOT NULL,
    MB_NICK             VARCHAR2(20)  UNIQUE      NOT NULL,
    MB_EMAIL            CHAR(1)                   NOT NULL,
    MB_ACCEPT      	    CHAR(1)  DEFAULT 'Y'  NOT NULL,
    MB_POINT            NUMBER DEFAULT 0          NOT NULL,
    MB_JOIN             DATE DEFAULT SYSDATE      NOT NULL,
    MB_UPDATE           DATE DEFAULT SYSDATE      NOT NULL,
    MB_AUTHCODE		    CHAR(1) DEFAULT 'N'				NOT NULL,
	MB_SESSIONKEY	    VARCHAR2(50), -- 세션ID 쿠키정보를 저장
	MB_SESSIONLIMIT    TIMESTAMP -- 7일에 해당되는 시간이 저장
	*/
	
	private String mb_id;
	private String mb_name;
	private String mb_pw;
	private String mb_postcode;
	private String mb_addr;
	private String mb_deaddr;
	private String mb_phone;
	private String mb_nick;
	private String mb_email;
	private String mb_accept;
	private int    mb_point;
	private Date   mb_join;
	private Date   mb_update;
	private String mb_authcode;
	private String mb_sessionkey;
	private Date   mb_sessionlimit;
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_postcode() {
		return mb_postcode;
	}
	public void setMb_postcode(String mb_postcode) {
		this.mb_postcode = mb_postcode;
	}
	public String getMb_addr() {
		return mb_addr;
	}
	public void setMb_addr(String mb_addr) {
		this.mb_addr = mb_addr;
	}
	public String getMb_deaddr() {
		return mb_deaddr;
	}
	public void setMb_deaddr(String mb_deaddr) {
		this.mb_deaddr = mb_deaddr;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_nick() {
		return mb_nick;
	}
	public void setMb_nick(String mb_nick) {
		this.mb_nick = mb_nick;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getMb_accept() {
		return mb_accept;
	}
	public void setMb_accept(String mb_accept) {
		this.mb_accept = mb_accept;
	}
	public int getMb_point() {
		return mb_point;
	}
	public void setMb_point(int mb_point) {
		this.mb_point = mb_point;
	}
	public Date getMb_join() {
		return mb_join;
	}
	public void setMb_join(Date mb_join) {
		this.mb_join = mb_join;
	}
	public Date getMb_update() {
		return mb_update;
	}
	public void setMb_update(Date mb_update) {
		this.mb_update = mb_update;
	}
	public String getMb_authcode() {
		return mb_authcode;
	}
	public void setMb_authcode(String mb_authcode) {
		this.mb_authcode = mb_authcode;
	}
	public String getMb_sessionkey() {
		return mb_sessionkey;
	}
	public void setMb_sessionkey(String mb_sessionkey) {
		this.mb_sessionkey = mb_sessionkey;
	}
	public Date getMb_sessionlimit() {
		return mb_sessionlimit;
	}
	public void setMb_sessionlimit(Date mb_sessionlimit) {
		this.mb_sessionlimit = mb_sessionlimit;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mb_id=" + mb_id + ", mb_name=" + mb_name + ", mb_pw=" + mb_pw + ", mb_postcode=" + mb_postcode
				+ ", mb_addr=" + mb_addr + ", mb_deaddr=" + mb_deaddr + ", mb_phone=" + mb_phone + ", mb_nick="
				+ mb_nick + ", mb_email=" + mb_email + ", mb_accept=" + mb_accept + ", mb_point=" + mb_point
				+ ", mb_join=" + mb_join + ", mb_update=" + mb_update + ", mb_authcode=" + mb_authcode
				+ ", mb_sessionkey=" + mb_sessionkey + ", mb_sessionlimit=" + mb_sessionlimit + "]";
	}
	
}
