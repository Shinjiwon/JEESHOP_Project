package com.jeeshop.domain;

public class OrderReadDetailVO {

	private int 	ord_amount;
	private int 	pro_num;
	private int 	ord_price;
	private int 	pro_price;
	private String 	pro_name;
	private String 	pro_img;
	
	public int getOrd_amount() {
		return ord_amount;
	}
	public void setOrd_amount(int ord_amount) {
		this.ord_amount = ord_amount;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
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
	
	@Override
	public String toString() {
		return "OrderReadDetailVO [ord_amount=" + ord_amount + ", pro_num=" + pro_num + ", ord_price=" + ord_price
				+ ", pro_price=" + pro_price + ", pro_name=" + pro_name + ", pro_img=" + pro_img + "]";
	}
}
