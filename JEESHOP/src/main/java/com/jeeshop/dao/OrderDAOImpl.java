package com.jeeshop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
