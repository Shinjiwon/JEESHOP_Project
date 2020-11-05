package com.jeeshop.domain;

public class CartProductVO {

	private int		cat_code;
	private int		cat_amount;
	private int		pro_num;
	private String	pro_name;
	private String 	pro_img;
	private int 	pro_price;
	private int		pro_discount;
	
	public int getCat_code() {
		return cat_code;
	}
	public void setCat_code(int cat_code) {
		this.cat_code = cat_code;
	}
	public int getCat_amount() {
		return cat_amount;
	}
	public void setCat_amount(int cat_amount) {
		this.cat_amount = cat_amount;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
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
	
	@Override
	public String toString() {
		return "CartProductVO [cat_code=" + cat_code + ", cat_amount=" + cat_amount + ", pro_num=" + pro_num
				+ ", pro_name=" + pro_name + ", pro_img=" + pro_img + ", pro_price=" + pro_price + ", pro_discount="
				+ pro_discount + "]";
	}
	
}
