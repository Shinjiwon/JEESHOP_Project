package com.jeeshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.ReviewDAO;
import com.jeeshop.domain.ReviewVO;
import com.jeeshop.util.Criteria;

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

	// 상품후기 리스트
	@Override
	public List<ReviewVO> reviewList(int pro_num, Criteria cri) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("pro_num", pro_num);
		map.put("cri", cri);
		
		return dao.reviewList(map);
	}

	// 상품후기 수정
	@Override
	public void reviewModify(ReviewVO vo) throws Exception {
		
		dao.reviewModify(vo);
	}

	// 상품후기 삭제
	@Override
	public void reviewDel(int rew_num) throws Exception {

		dao.reviewDel(rew_num);
	}

}
