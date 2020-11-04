package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

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

	// 상품후기 리스트
	@Override
	public List<ReviewVO> reviewList(Map<String, Object> map) throws Exception {

		return session.selectList(NS+".reviewList", map);
	}

	// 상품후기 수정
	@Override
	public void reviewModify(ReviewVO vo) throws Exception {
		
		session.update(NS+".reviewModify", vo);
	}

	// 상품후기 삭제
	@Override
	public void reviewDel(int rew_num) throws Exception {

		session.delete(NS+".reviewDel", rew_num);
	}

}
