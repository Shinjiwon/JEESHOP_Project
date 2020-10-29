package com.jeeshop.domain;

import java.util.Date;

public class ReviewVO {

	/*
  	REW_NUM     NUMBER  PRIMARY KEY,
    MB_ID       VARCHAR2(50)            NOT NULL,
    PRO_NUM     NUMBER                  NOT NULL,
    REW_CONTENT VARCHAR2(2000)          NOT NULL,
    REW_SCORE   NUMBER                  NOT NULL,
    REW_DATE    DATE DEFAULT SYSDATE    NOT NULL
	 */
	
	private int 	rew_num;
	private String 	mb_id;
	private int		pro_num;
	private String	rew_content;
	private int 	rew_score;
	private Date	rew_date;
	
	public int getRew_num() {
		return rew_num;
	}
	public void setRew_num(int rew_num) {
		this.rew_num = rew_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getRew_content() {
		return rew_content;
	}
	public void setRew_content(String rew_content) {
		this.rew_content = rew_content;
	}
	public int getRew_score() {
		return rew_score;
	}
	public void setRew_score(int rew_score) {
		this.rew_score = rew_score;
	}
	public Date getRew_date() {
		return rew_date;
	}
	public void setRew_date(Date rew_date) {
		this.rew_date = rew_date;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [rew_num=" + rew_num + ", mb_id=" + mb_id + ", pro_num=" + pro_num + ", rew_content="
				+ rew_content + ", rew_score=" + rew_score + ", rew_date=" + rew_date + "]";
	}
}
