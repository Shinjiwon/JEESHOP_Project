package com.jeeshop.dao;

import java.util.List;

import com.jeeshop.domain.OrderDetailVO;
import com.jeeshop.domain.OrderListVO;
import com.jeeshop.domain.OrderReadDetailVO;
import com.jeeshop.domain.OrderVO;

public interface OrderDAO {
	
	// 주문번호 시퀀스 가져오기
	public int readOrderNum() throws Exception;
	
	// 주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
	
	// 주문내역 추가
	public void addOrderDetail(OrderDetailVO vo) throws Exception;
	
	// 주문 목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문상세 페이지
	public List<OrderReadDetailVO> readOrder(int ord_num) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int ord_num) throws Exception;
}
