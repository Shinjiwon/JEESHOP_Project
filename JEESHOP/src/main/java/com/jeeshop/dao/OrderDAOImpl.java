package com.jeeshop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.OrderDetailVO;
import com.jeeshop.domain.OrderListVO;
import com.jeeshop.domain.OrderReadDetailVO;
import com.jeeshop.domain.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SqlSession session;
	
	private final static String NS = "com.jeeshop.mappers.OrderMapper";

	// 주문번호 시퀀스 가져오기
	@Override
	public int readOrderNum() throws Exception {
		
		return session.selectOne(NS+".readOrderNum");
	}

	// 주문정보 추가
	@Override
	public void addOrder(OrderVO vo) throws Exception {

		session.insert(NS+".addOrder", vo);
	}

	// 주문내역 추가
	@Override
	public void addOrderDetail(OrderDetailVO vo) throws Exception {

		session.insert(NS+".addOrderDetail", vo);
	}

	// 주문 목록
	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		
		return session.selectList(NS+".orderList", mb_id);
	}

	// 주문상세 페이지
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_num) throws Exception {
		
		return session.selectList(NS+".readOrder", ord_num);
	}

	// 주문자 정보
	@Override
	public OrderVO getOrder(int ord_num) throws Exception {
		
		return session.selectOne(NS+".getOrder", ord_num);
	}
}
