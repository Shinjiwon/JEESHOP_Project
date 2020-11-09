package com.jeeshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeeshop.domain.OrderDetailVO;
import com.jeeshop.domain.OrderVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.dto.MemberDTO;
import com.jeeshop.service.MemberService;
import com.jeeshop.service.OrderService;
import com.jeeshop.service.ProductService;

@Controller
@RequestMapping("/order/*")
public class OrderController {

	@Inject
	private OrderService service;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	// ▶ 상품상세 페이지 → 구매 버튼 클릭 시
	/*
	 * Model 객체로 상품리스트, 수량, 구매자 정보 전달
	 */
	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public void buyGET(@RequestParam int ord_amount, 
					   @RequestParam int pro_num, Model model, HttpSession session) throws Exception {
		
		logger.info("=====buyGET execute()...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amounList = new ArrayList<Integer>();
		
		productList.add(productService.proRead(pro_num));
		amounList.add(ord_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amounList", amounList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
	}
	
	// ▶ 상품 상세페이지 → 구매 버튼 → 결제하기
	public String orderPOST(OrderVO order, 
							OrderDetailVO orderDetailList, 
							HttpSession session) throws Exception {
								
		logger.info("=====orderPOST execute()...");
		
		logger.info("=====OrderVO(주문자 정보): " + order.toString());
		logger.info("=====OrderDetailVO(주문 상세 내역): " + orderDetailList.toString());
		
		
		return null;
	}
}
