package com.jeeshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeshop.dao.OrderDAO;
import com.jeeshop.domain.OrderDetailVO;
import com.jeeshop.domain.OrderDetailVOList;
import com.jeeshop.domain.OrderListVO;
import com.jeeshop.domain.OrderReadDetailVO;
import com.jeeshop.domain.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartService;

	// 주문정보 추가
	/*
	 * @Transactional → 메서드에서 2가지 이상의 기능이 사용될 때. Insert, Update, Delete
	 */
	@Transactional
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
			dao.addOrderDetail(orderDetail);
		}
	}

	// 주문정보 추가(장바구니)
	@Transactional
	@Override
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception {

		// 시퀀스(주문번호) 가져오기
		int ord_num = dao.readOrderNum();
		
		// 주문 정보 추가
		order.setOrd_num(ord_num);
		
		// 1)주문정보 저장
		dao.addOrder(order);
		// 2)주문내역 추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_num(ord_num);
			
			// 주문상세정보 저장
			dao.addOrderDetail(orderDetail);
			
			// 장바구니 테이블에서 해당 상품들 삭제
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mb_id", mb_id);
			map.put("pro_num", orderDetail.getPro_num());
			
			// 장바구니 삭제(상세페이지 구매와 다른 점)
			cartService.deleteCartOrder(map);
		}
		
	}

	// 주문 목록
	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		
		return dao.orderList(mb_id);
	}

	// 주문상세 페이지
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_num) throws Exception {
		
		return dao.readOrder(ord_num);
	}

	// 주문자 정보
	@Override
	public OrderVO getOrder(int ord_num) throws Exception {
		
		return dao.getOrder(ord_num);
	}
	
}