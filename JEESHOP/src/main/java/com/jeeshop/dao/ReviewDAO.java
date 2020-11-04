package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import com.jeeshop.domain.ReviewVO;

public interface ReviewDAO {
	
	// 상품후기 개수
	public int reviewCount(int pro_num) throws Exception;

	// 상품후기 쓰기
	public void writeReview(ReviewVO vo) throws Exception;
	
	// 상품후기 리스트
	public List<ReviewVO> reviewList(Map<String, Object> map) throws Exception;
	
	// 상품후기 수정
	public void reviewModify(ReviewVO vo) throws Exception;
	
	// 상품후기 삭제
	public void reviewDel(int rew_num) throws Exception;
}
