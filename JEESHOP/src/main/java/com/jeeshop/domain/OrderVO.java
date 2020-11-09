package com.jeeshop.domain;

import java.util.Date;

public class OrderVO {

	/*
 	ORD_NUM         NUMBER  PRIMARY KEY NOT NULL,
    MB_ID           VARCHAR2(50)        NOT NULL,
    ORD_NAME        VARCHAR2(30)        NOT NULL,
    ORD_ZIPCODE     CHAR(5)             NOT NULL,
    ORD_ADDR        VARCHAR2(50)        NOT NULL,
    ORD_DETADDR     VARCHAR2(50)        NOT NULL,
    ORD_PHONE       VARCHAR2(20)        NOT NULL,
    ORD_PRICE       NUMBER              NOT NULL,
    ORD_DATE        DATE DEFAULT SYSDATE
    -- ORD_STATE → 배송상태
	 */
	
	private int 	ord_num;
	private String 	mb_id;
	private String	ord_name;
	private	String 	ord_postcode;
	private String 	ord_addr;
	private String	ord_deaddr;
	private	String	ord_phone;
	private int 	ord_price;
	private Date	ord_date;
	
	public int getOrd_num() {
		return ord_num;
	}
	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getOrd_name() {
		return ord_name;
	}
	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}
	public String getOrd_postcode() {
		return ord_postcode;
	}
	public void setOrd_postcode(String ord_postcode) {
		this.ord_postcode = ord_postcode;
	}
	public String getOrd_addr() {
		return ord_addr;
	}
	public void setOrd_addr(String ord_addr) {
		this.ord_addr = ord_addr;
	}
	public String getOrd_deaddr() {
		return ord_deaddr;
	}
	public void setOrd_deaddr(String ord_deaddr) {
		this.ord_deaddr = ord_deaddr;
	}
	public String getOrd_phone() {
		return ord_phone;
	}
	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	
	@Override
	public String toString() {
		return "OrderVO [ord_num=" + ord_num + ", mb_id=" + mb_id + ", ord_name=" + ord_name + ", ord_postcode="
				+ ord_postcode + ", ord_addr=" + ord_addr + ", ord_deaddr=" + ord_deaddr + ", ord_phone=" + ord_phone
				+ ", ord_price=" + ord_price + ", ord_date=" + ord_date + "]";
	}
}
