package com.jeeshop.domain;

import java.util.Date;

public class AdOrderVO {

	private int 	ord_num;
	private String 	mb_id;
	private	String	ord_addr;
	private String	ord_deaddr;
	private String	ord_phone;
	private String 	pro_name;
	private String 	pro_img;
	private int 	ord_amount;
	private int		ord_price;
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
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	public int getOrd_amount() {
		return ord_amount;
	}
	public void setOrd_amount(int ord_amount) {
		this.ord_amount = ord_amount;
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
		return "AdOrderVO [ord_num=" + ord_num + ", mb_id=" + mb_id + ", ord_addr=" + ord_addr + ", ord_deaddr="
				+ ord_deaddr + ", ord_phone=" + ord_phone + ", pro_name=" + pro_name + ", pro_img=" + pro_img
				+ ", ord_amount=" + ord_amount + ", ord_price=" + ord_price + ", ord_date=" + ord_date + "]";
	}
	
}
