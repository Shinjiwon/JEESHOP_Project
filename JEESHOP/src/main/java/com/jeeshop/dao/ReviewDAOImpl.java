package com.jeeshop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.ReviewVO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
	private SqlSession session;
	private final static String NS = "com.jeeshop.mappers.ReviewMapper";
	
	// 상품후기 개수
	@Override
	public int reviewCount(int pro_num) throws Exception {

		return session.selectOne(NS+".reviewCount", pro_num);
	}
	
	// 상품후기 쓰기
	@Override
	public void writeReview(ReviewVO vo) throws Exception {

		session.insert(NS+".writeReview", vo);
	}

}
