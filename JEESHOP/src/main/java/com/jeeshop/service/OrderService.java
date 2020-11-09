package com.jeeshop.service;

import com.jeeshop.domain.OrderDetailVOList;
import com.jeeshop.domain.OrderVO;

public interface OrderService {

	// 주문정보 추가
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
}
