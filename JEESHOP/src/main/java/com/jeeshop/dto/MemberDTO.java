package com.jeeshop.dto;

public class MemberDTO {
	
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String mb_nick;
	private String mb_point;
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pw() {
		return mb_pw;
	}
	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_nick() {
		return mb_nick;
	}
	public void setMb_nick(String mb_nick) {
		this.mb_nick = mb_nick;
	}
	public String getMb_point() {
		return mb_point;
	}
	public void setMb_point(String mb_point) {
		this.mb_point = mb_point;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [mb_id=" + mb_id + ", mb_pw=" + mb_pw + ", mb_name=" + mb_name + ", mb_nick=" + mb_nick
				+ ", mb_point=" + mb_point + "]";
	}
}
