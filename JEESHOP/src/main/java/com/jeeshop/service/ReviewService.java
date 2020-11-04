package com.jeeshop.service;

import java.util.List;

import com.jeeshop.domain.ReviewVO;
import com.jeeshop.util.Criteria;

public interface ReviewService {
	
	// 상품후기 개수
	public int reviewCount(int pro_num) throws Exception;

	// 상품후기 쓰기
	public void writeReview(ReviewVO vo, String mb_id) throws Exception;
	
	// 상품후기 리스트
	public List<ReviewVO> reviewList(int pro_num, Criteria cri) throws Exception;
	
	// 상품후기 수정
	public void reviewModify(ReviewVO vo) throws Exception;
	
	// 상품후기 삭제
	public void reviewDel(int rew_num) throws Exception;
}
