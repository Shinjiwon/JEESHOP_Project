package com.jeeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.ReviewDAO;
import com.jeeshop.domain.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	// 상품후기 개수
	@Override
	public int reviewCount(int pro_num) throws Exception {

		return dao.reviewCount(pro_num);
	}

	// 상품후기 쓰기
	@Override
	public void writeReview(ReviewVO vo, String mb_id) throws Exception {

		vo.setMb_id(mb_id);
		dao.writeReview(vo);
	}

}
