package com.jeeshop.domain;

public class OrderDetailVO {

	/*
  	ORD_NUM     NUMBER,
    PRO_NUM     NUMBER,
    ORD_AMOUNT NUMBER  NOT NULL,
    ORD_PRICE  NUMBER  NOT NULL,
    PRIMARY KEY(ORD_NUM, PRO_NUM)
	 */
	
	private int	ord_num;
	private int	pro_num;
	private int ord_amount;
	private int	ord_price;
	
	public int getOrd_num() {
		return ord_num;
	}
	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
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
	
	@Override
	public String toString() {
		return "OrderDetailVO [ord_num=" + ord_num + ", pro_num=" + pro_num + ", ord_amount=" + ord_amount
				+ ", ord_price=" + ord_price + "]";
	}
}
