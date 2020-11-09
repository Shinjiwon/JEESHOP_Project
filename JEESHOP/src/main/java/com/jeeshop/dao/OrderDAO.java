package com.jeeshop.dao;

import com.jeeshop.domain.OrderVO;

public interface OrderDAO {
	
	// 주문번호 시퀀스 가져오기
	public int readOrderNum() throws Exception;
	
	// 주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
}
