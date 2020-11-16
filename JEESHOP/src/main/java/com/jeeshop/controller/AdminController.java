package com.jeeshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.dto.AdminDTO;
import com.jeeshop.service.AdminService;
import com.jeeshop.service.MemberService;
import com.jeeshop.util.Criteria;
import com.jeeshop.util.PageMaker;

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
	
	// ▶ 로그인 페이지
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("=====loginGET execute()...");
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
			
			// 관리자 로그인 시간 업데이트
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
	
	// ▶ 회원목록 페이지
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public void userInfoGET(@ModelAttribute("cri") Criteria cri, 
								Model model) throws Exception {
		 
		logger.info("=====userInfoGET execute()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		model.addAttribute("userList", service.UserInfoList());
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.userCount();
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
}