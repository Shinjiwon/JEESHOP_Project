package com.jeeshop.service;

import com.jeeshop.domain.ReviewVO;

public interface ReviewService {
	
	// 상품후기 개수
	public int reviewCount(int pro_num) throws Exception;

	// 상품후기 쓰기
	public void writeReview(ReviewVO vo, String mb_id) throws Exception;
}
