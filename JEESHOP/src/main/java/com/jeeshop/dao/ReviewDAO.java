package com.jeeshop.dao;

import com.jeeshop.domain.ReviewVO;

public interface ReviewDAO {
	
	// 상품후기 개수
	public int reviewCount(int pro_num) throws Exception;

	// 상품후기 쓰기
	public void writeReview(ReviewVO vo) throws Exception;
}
