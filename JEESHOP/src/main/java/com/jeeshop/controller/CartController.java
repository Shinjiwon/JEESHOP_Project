package com.jeeshop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.domain.CartVO;
import com.jeeshop.dto.MemberDTO;
import com.jeeshop.service.CartService;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	private CartService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	// ▶ 장바구니 상품추가 → 상품수량 1개
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseEntity<String> addCart(int pro_num, HttpSession session) {
		
		logger.info("=====addCart execute()...");
		logger.info("=====pro_num: " + pro_num);
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		vo.setMb_id(dto.getMb_id());
		vo.setPro_num(pro_num);
		vo.setCat_amount(1);
		
		logger.info("=====VO: " + vo.toString());
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}

	// ▶ 장바구니 상품추가 → 상품수량 여러개
	@ResponseBody
	@RequestMapping(value = "addMany", method = RequestMethod.POST)
	public ResponseEntity<String> addManyCart(int pro_num, int pro_amount, HttpSession session) {
		
		logger.info("=====addManyCart execute()...");
		logger.debug("=====pro_num: " + pro_num);
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		vo.setMb_id(dto.getMb_id());
		vo.setPro_num(pro_num);
		vo.setCat_amount(pro_amount);
		
		logger.info("=====VO: " + vo);

		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	// ▶ 장바구니 리스트
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void cartListGET(Model model, HttpSession session) throws Exception {
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		model.addAttribute("cartProductList", service.getCart(dto.getMb_id()));
	}
}
