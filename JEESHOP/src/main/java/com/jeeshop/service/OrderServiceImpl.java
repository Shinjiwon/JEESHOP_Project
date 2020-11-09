package com.jeeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.OrderDAO;
import com.jeeshop.domain.OrderDetailVO;
import com.jeeshop.domain.OrderDetailVOList;
import com.jeeshop.domain.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartService;

	// 주문정보 추가
	@Override
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception {
		
		// 시퀀스 처리하는 방법 2가지
		
		// 시퀀스(주문번호) 가져요기 → 주문정보와 주문상세정보에 동일한 주문코드가 사용되어야 하기 때문
		int ord_num = dao.readOrderNum();
		
		// 주문정보 추가하기
		order.setOrd_num(ord_num);
		dao.addOrder(order); // 주문테이블에 데이터 삽입하기.
		
		// 주문상세 추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		
		// 주문상세 수 만큼 반복 작업
		for(int i=0; i<list.size(); i++) {
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_num(ord_num);
			
		}
	}

	
}
