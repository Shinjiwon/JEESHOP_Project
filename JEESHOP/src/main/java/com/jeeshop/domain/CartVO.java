package com.jeeshop.domain;

public class CartVO {

	/*
	CAT_CODE    NUMBER  PRIMARY KEY,
    PRO_NUM     NUMBER          NOT NULL,
    MB_ID       VARCHAR2(20)    NOT NULL,
    CAT_AMOUNT  NUMBER          NOT NULL
	 */
	
	private int 	cat_code;
	private int 	pro_num;
	private String	mb_id;
	private int 	cat_amount;
	
	public int getCat_code() {
		return cat_code;
	}
	public void setCat_code(int cat_code) {
		this.cat_code = cat_code;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getCat_amount() {
		return cat_amount;
	}
	public void setCat_amount(int cat_amount) {
		this.cat_amount = cat_amount;
	}
	
	@Override
	public String toString() {
		return "CartVO [cat_code=" + cat_code + ", pro_num=" + pro_num + ", mb_id=" + mb_id + ", cat_amount="
				+ cat_amount + "]";
	}
	
}
