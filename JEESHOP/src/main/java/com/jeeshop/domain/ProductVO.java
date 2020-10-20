package com.jeeshop.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {
	
	/*
	PRO_NUM         NUMBER  PRIMARY KEY,
    CATE_CODE       VARCHAR2(20)            NOT NULL,
    CATE_PRTCODE    VARCHAR2(20)            NOT NULL,
    PRO_NAME        VARCHAR2(50)            NOT NULL,
    PRO_PRICE       NUMBER                  NOT NULL,
    PRO_DISCOUNT    NUMBER                  NOT NULL,
    PRO_DEV         VARCHAR2(30)            NOT NULL,
    PRO_DETAIL      CLOB                    NOT NULL,
    PRO_IMG         VARCHAR(50)             NOT NULL,
    PRO_AMOUNT      NUMBER                  NOT NULL,
    PRO_BUY         CHAR(1)                 NOT NULL,
    PRO_DATE        DATE    DEFAULT SYSDATE NOT NULL,
    PRO_UPDATE      DATE    DEFAULT SYSDATE NOT NULL
	 */
	
	private int 	pro_num;
	private String 	cate_code;
	private String 	cate_prtcode;
	private String 	pro_name;
	private int 	pro_price;
	private int 	pro_discount;
	private String	pro_dev;
	private String	pro_detail;
	private String	pro_img;
	private int 	pro_amount;
	private String 	pro_buy;
	private Date 	pro_date;
	private Date 	pro_update;
	
	// 업로드 파일
	private MultipartFile file1;  // insert.jsp <input type="file" id="file1" name="file1" class="form-control" />

	public int getPro_num() {
		return pro_num;
	}

	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}

	public String getCate_code() {
		return cate_code;
	}

	public void setCate_code(String cate_code) {
		this.cate_code = cate_code;
	}

	public String getCate_prtcode() {
		return cate_prtcode;
	}

	public void setCate_prtcode(String cate_prtcode) {
		this.cate_prtcode = cate_prtcode;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public int getPro_discount() {
		return pro_discount;
	}

	public void setPro_discount(int pro_discount) {
		this.pro_discount = pro_discount;
	}

	public String getPro_dev() {
		return pro_dev;
	}

	public void setPro_dev(String pro_dev) {
		this.pro_dev = pro_dev;
	}

	public String getPro_detail() {
		return pro_detail;
	}

	public void setPro_detail(String pro_detail) {
		this.pro_detail = pro_detail;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public int getPro_amount() {
		return pro_amount;
	}

	public void setPro_amount(int pro_amount) {
		this.pro_amount = pro_amount;
	}

	public String getPro_buy() {
		return pro_buy;
	}

	public void setPro_buy(String pro_buy) {
		this.pro_buy = pro_buy;
	}

	public Date getPro_date() {
		return pro_date;
	}

	public void setPro_date(Date pro_date) {
		this.pro_date = pro_date;
	}

	public Date getPro_update() {
		return pro_update;
	}

	public void setPro_update(Date pro_update) {
		this.pro_update = pro_update;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	@Override
	public String toString() {
		return "ProductVO [pro_num=" + pro_num + ", cate_code=" + cate_code + ", cate_prtcode=" + cate_prtcode
				+ ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_discount=" + pro_discount
				+ ", pro_dev=" + pro_dev + ", pro_detail=" + pro_detail + ", pro_img=" + pro_img + ", pro_amount="
				+ pro_amount + ", pro_buy=" + pro_buy + ", pro_date=" + pro_date + ", pro_update=" + pro_update
				+ ", file1=" + file1 + "]";
	}
}
