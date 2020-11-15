package com.jeeshop.service;

import java.util.List;

import com.jeeshop.domain.OrderDetailVOList;
import com.jeeshop.domain.OrderListVO;
import com.jeeshop.domain.OrderReadDetailVO;
import com.jeeshop.domain.OrderVO;

public interface OrderService {

	// 주문정보 추가
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
	
	// 주문정보 추가(장바구니)
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception;
	
	// 주문 목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문상세 페이지
	public List<OrderReadDetailVO> readOrder(int ord_num) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int ord_num) throws Exception;
}
