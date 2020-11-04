package com.jeeshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.domain.ReviewVO;
import com.jeeshop.dto.MemberDTO;
import com.jeeshop.service.ReviewService;
import com.jeeshop.util.Criteria;
import com.jeeshop.util.PageMaker;

@Controller
@RequestMapping("/review/*")
public class ReviewController {
	
	@Inject
	private ReviewService service;

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	// ▶ 상품후기 쓰기
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) throws Exception {
		
		logger.info("=====write execute()...");
		logger.info("=====VO: " + vo.toString());
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		service.writeReview(vo, dto.getMb_id());
	}
	
	// ▶ 상품후기 리스트
	@ResponseBody
	@RequestMapping(value = "{pro_num}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> reviewList(
							@PathVariable("pro_num") Integer pro_num,
							@PathVariable("page") Integer page) {
		
		logger.info("=====reviewList execute()...");
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			// PageMaker 객체 모델 작업
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			// 상품후기 리스트 모델 작업
			// 상품 후기 리스트 VO
			List<ReviewVO> list = service.reviewList(pro_num, cri);
			
			// 후기 목록 jsp추가 하기 위한 작업
			map.put("list", list);
			
			// 총 후기 개수 → PageMaker
			int reviewCount = service.reviewCount(pro_num);
			pageMaker.setTotalCount(reviewCount);
			
			// 리스트 하단에 페이지 추가
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
								
		return entity;
	}
	
	// ▶ 상품후기 수정
	@ResponseBody
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> reviewModify(ReviewVO vo) {
		
		logger.info("=====reviewModify execute()...");
		logger.info("=====VO: " + vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.reviewModify(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	// ▶ 상품후기 삭제
	@ResponseBody
	@RequestMapping(value = "{rew_num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> reviewDel(@PathVariable("rew_num") int rew_num) {
		
		logger.info("=====reviewDel execute()...");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.reviewDel(rew_num);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
