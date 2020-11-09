package com.jeeshop.domain;

import java.util.List;

public class OrderDetailVOList {

	// List<>컬렉션으로 데이터 받기
	private List<OrderDetailVO> orderDetailList;

	// Getter&Setter
	public List<OrderDetailVO> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailVO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	// toString()
	@Override
	public String toString() {
		return "OrderDetailVOList [orderDetailList=" + orderDetailList + "]";
	}
}
