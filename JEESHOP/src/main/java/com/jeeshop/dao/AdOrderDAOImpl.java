package com.jeeshop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.OrderVO;

@Repository
public class AdOrderDAOImpl implements AdOrderDAO {

	@Inject
	private SqlSession session;
	
	public final static String NS = "com.jeeshop.mappers.AdOrderMapper";

	// 주문목록 가져오기
	@Override
	public List<OrderVO> orderList() throws Exception {
		
		return session.selectList(NS+".orderList");
	}
}
