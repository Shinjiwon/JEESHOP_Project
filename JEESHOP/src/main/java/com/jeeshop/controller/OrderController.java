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

import com.jeeshop.domain.OrderDetailVOList;
import com.jeeshop.domain.OrderListVO;
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
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.proRead(pro_num));
		amountList.add(ord_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
	}
	
	// ▶ 상품 상세페이지 → 구매 버튼 → 결제하기
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String orderPOST(OrderVO order, 
							OrderDetailVOList orderDetailList, 
							HttpSession session) throws Exception {
								
		logger.info("=====orderPOST execute()...");
		
		logger.info("=====OrderVO(주문자 정보): " + order.toString());
		logger.info("=====OrderDetail(주문 상세 내역): " + orderDetailList.toString());
		
		service.addOrder(order, orderDetailList);
		
		return "/order/orderComplete";
	}
	
	// ▶ 장바구니 → 상품 구매하기
	@RequestMapping(value = "buyFromCart", method = RequestMethod.GET)
	public String buyFromCartGET(@RequestParam int ord_mount,
								@RequestParam int pro_num, Model model, HttpSession session) throws Exception {
		
		logger.info("=====buyFromCartGET execute()...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.proRead(pro_num));
		amountList.add(ord_mount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
		
		return "/order/buyFromCart";
	}
	
	// ▶ 장바구니 → 체크 상품 구매하기
	@RequestMapping(value = "buyFromCart", method = RequestMethod.POST)
	public void buyFromCartPOST(@RequestParam("check") List<Integer> checkList,
								@RequestParam("pro_num") List<Integer> pro_numList,
								@RequestParam("cat_amount") List<Integer> cat_amountList,
								@RequestParam("cat_code") List<Integer> cat_codeList,
								Model model, HttpSession session) throws Exception {
		
		logger.info("=====buyFromCartPOST execute()...");
		
		// 선택된 상품코드, 수량정보 작업
		// 상품정보를 저장하기 위한 컬렉션 생성 → 체크박스에 선택된 행의 상품코드의 정보를 DB에서 가져와서 저장
		List<ProductVO> productList = new ArrayList<ProductVO>();
		// 선택된 행의 상품의 변경된 수량
		List<Integer> amountList = new ArrayList<Integer>();
		
		// 장바구니 목록에서 체크된 값만을 선택하여 List에 추가
		for(int i=0; i<cat_codeList.size(); i++) { // 일반적인 전송 3개
			for(int j=0; j<checkList.size(); j++) { // 체크박스 전송된 정보 2개
				// 2번 true
				if((int)cat_codeList.get(i)==(int)checkList.get(j)) {
					// 선택된 행의 상품 코드를 DB에서 가져와 컬렉션에 추가
					productList.add(productService.proRead((int)pro_numList.get(i)));
					// cat_amountList.get(i): 선택된 행의 변경된 수량
					amountList.add(cat_amountList.get(i));
					
					continue;
					
				} else {
					continue;
				}
				
			}
			
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
	}
	
	// ▶ 장바구니 → 상품구매 하기 → 결제하기
	@RequestMapping(value = "orderFromCart", method = RequestMethod.POST)
	public String orderFromCartPOST(OrderVO order, OrderDetailVOList orderDetailList,
									Model model, HttpSession session) throws Exception {
		
		logger.info("=====orderFromCartPOST execute()...");
		
		logger.info("=====OrderVO(주문자 정보): " + order.toString());
		logger.info("=====OrderDetail(주문 상세 내역): " + orderDetailList.toString());
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		service.addOrderCart(order, orderDetailList, dto.getMb_id());
		
		return "/order/orderComplete";
	}
	
	// ▶ 주문목록 조회
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void orderListGET(Model model, HttpSession session) throws Exception {
		
		logger.info("=====orderListGET execute()...");
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		List<OrderListVO> list = service.orderList(dto.getMb_id());
		
		model.addAttribute("orderList", list);
	}
	
	// ▶ 주문상세 페이지
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void readGET(int ord_num,Model model, HttpSession session) throws Exception {
		 
		logger.info("=====readGET execute()...");
		
		model.addAttribute("orderList", service.readOrder(ord_num));
		model.addAttribute("order", service.getOrder(ord_num));
	}
}
