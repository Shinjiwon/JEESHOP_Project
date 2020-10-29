package com.jeeshop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeeshop.domain.ReviewVO;
import com.jeeshop.dto.MemberDTO;
import com.jeeshop.service.ReviewService;

@Controller
@RequestMapping("/review/*")
public class ReviewController {
	
	@Inject
	private ReviewService service;

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	// ▶ 상품후기 쓰기
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) throws Exception {
		
		logger.info("=====write execute()...");
		logger.info("=====VO: " + vo.toString());
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		service.writeReview(vo, dto.getMb_id());
	}
}
