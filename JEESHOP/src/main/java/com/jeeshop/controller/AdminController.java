package com.jeeshop.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.dto.AdminDTO;
import com.jeeshop.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// ▶ 메인 페이지
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String adminMain() {
		
		logger.info("=====adminMain execute()...");
		 
		return "admin/main";
	}
	
	// ▶ 관리자 로그인
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPOST(AdminDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {

		logger.info("=====loginPOST execute()");
		logger.info("AdminDTO: " + dto.toString());
		
		AdminVO vo = null;
		vo = service.login(dto);
		String msg = "";
		
		if(vo != null) { // 로그인 성공
			
			service.loginUpdate(dto.getAdmin_id());
			// 세션정보 저장 시 사용자로그인 key와 달라야한다.
			session.setAttribute("admin", vo);
			
			msg = "LOGIN_SUCCESS";
			
		} else { // 로그인 실패
			msg = "LOGIN_FAIL";
		}
		
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/admin/main";
	}
	
	// ▶ 관리자 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		logger.info("=====logout execute()...");
		
		session.invalidate();
		rttr.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/admin/main";
	}
}