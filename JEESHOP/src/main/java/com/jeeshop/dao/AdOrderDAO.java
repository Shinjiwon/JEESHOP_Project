package com.jeeshop.dao;

import java.util.List;

import com.jeeshop.domain.OrderVO;

public interface AdOrderDAO {

	// 주문목록 가져오기
	public List<OrderVO> orderList() throws Exception;
}
