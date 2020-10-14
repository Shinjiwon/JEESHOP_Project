package com.jeeshop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.MemberDTO;
import com.jeeshop.service.MemberService;

@Controller
@RequestMapping("/member/*") // 공통경로
public class MemberController {
	
	@Inject
	MemberService service;
	
	@Inject
	private BCryptPasswordEncoder crptPassEnc; // 비밀번호 암호화에 필요
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// ▶ 회원가입 페이지(GET)
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET() {
		
		logger.info("=====joinGET execute()...");
	}
	
	// ▶ 회원가입 진행(POST)
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO vo, RedirectAttributes rttr) throws Exception {

		// 비밀번호 암호화 작업
		vo.setMb_pw(crptPassEnc.encode(vo.getMb_pw()));
		
		service.join(vo);
		rttr.addFlashAttribute("msg", "JOIN SUCCESS");
		
		return "redirect:/"; // main페이지 이동
	}
	
	// ▶ 아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "idDuplicateChk", method = RequestMethod.POST)
	public ResponseEntity<String> idDuplicateChk(@RequestParam("mb_id") String mb_id) throws Exception {
		
		logger.info("=====idDuplicateChk execute()...");
		ResponseEntity<String> entity = null;
		
		try {
			String chk_id = service.idDuplicateChk(mb_id);
			
			// 아이디 존재여부 확인
			if(chk_id != null) { // 아이디 존재 → 사용 불가
				
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
				
			} else { // 아이디 사용가능
				
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ▶ 이메일 인증코드 확인. 세션에 저장한 인증코드 읽어오기.
	@ResponseBody
	@RequestMapping(value = "emailAuthcodeChk", method = RequestMethod.POST)
	public ResponseEntity<String> emailAuthcodeChk(@RequestParam("code") String code, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				// 인증코드 일치
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				
			} else {
				// 인증코드 불일치
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// ▶ 로그인 페이지(GET)
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET() {
		
		logger.info("=====loginGET execute()...");
	}
	
	// ▶ 로그인 진행(POST)
	/*
	 MemberVO말고 MemberDTO사용하는 이유 
	  → MemberVO안에 있는 변수를 다 사용할 필요 없기 때문
	  
	  * 쿠키 작업 하지 말 것
	  
	  * RedirectAttributes 클래스: 이동되는 주소에 데이터를 사용하게 하기 위하여 
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPOST(MemberDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		logger.info("loginPOST execute()...");
		
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) {
			logger.info("=====로그인 성공");
			
			session.setAttribute("user", memDTO);
			
			rttr.addFlashAttribute("msg", "LOGIN SUCCESS");
			return "redirect:/";
			
		} else {
			logger.info("=====로그인 실패");

			rttr.addFlashAttribute("msg", "LOGIN FAIL");
			return "redirect:/member/login";
		}
		
	}
	
	// ▶ 로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		
		logger.info("=====logout execute()");
		
		session.invalidate(); // 세션에 저장된 메모리 제거
		rttr.addFlashAttribute("msg", "LOGOUT SUCCESS");
		
		return "redirect:/";
		
	}
	
	// ▶ 비밀번호 재확인
	@RequestMapping(value = "pwChk", method = RequestMethod.GET)
	public void pwChkGET(@ModelAttribute("url") String url ) {
		logger.info("=====pwChkGET execute()...");
	}
	
	// ▶ 비밀번호 재확인 후  
	// 1)회원정보 수정 2)비밀번호 변경
	@RequestMapping(value = "pwChk", method = RequestMethod.POST)
	public String pwChkPOST(@RequestParam("url") String url,
							@RequestParam("mb_pw") String mb_pw,
							HttpSession session, Model model) throws Exception {
		
		logger.info("=====pwChkPOST execute()...");
		logger.info("=====url: " + url + ", mb_pw: " + mb_pw);
		
		// 로그인할 때 세션에 저장한 정보
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		// 비밀번호 일치 확인
		if(crptPassEnc.matches(mb_pw, dto.getMb_pw())) { // 비밀번호 일치
			// 회원정보 수정 페이지
			if(url.equals("modify")) {
			model.addAttribute("vo", service.readUserInfo(dto.getMb_id()));
			return "/member/modify";
			
			} else if (url.equals("pwChange")) {
				return "/member/pwChange";
			}
		}
		
		// 비밀번호 일치하지 않을 경우
		model.addAttribute("url", url);
		model.addAttribute("msg", "PW_CHECK_FAIL");
		return "/member/pwChk";
	
	}
	
	// ▶ 비밀번호 변경 시 현재 비밀번호 확인(ajax)
	@ResponseBody
	@RequestMapping("checkAjax")
	public ResponseEntity<String> checkAjax(@RequestParam("mb_pw") String mb_pw, HttpSession session) {
		
		logger.info("=====checkAjax execute()...");
		
		ResponseEntity<String> entity = null;
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		logger.info("=====mb_pw: " + mb_pw);
		logger.info("=====dto: " + dto.toString());
		
		if(crptPassEnc.matches(mb_pw, dto.getMb_pw())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		
		return entity;
	}
	
	// ▶ 회원정보 수정
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		logger.info("=====modifyPOST execute()...");
		
		service.modifyUserInfo(vo); // 회원정보 수정
		
		rttr.addFlashAttribute("msg", "MODIFY_SUCCESS");
		
		return "redirect:/";
	}
	
	// ▶ 비밀번호 변경
	@RequestMapping(value = "pwChange", method = RequestMethod.POST)
	public String pwChangePOST(MemberDTO dto, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		logger.info("=====pwChangePOST execute()...");
		
		dto.setMb_pw(crptPassEnc.encode(dto.getMb_pw()));
		service.pwChange(dto);
		
		// ★★★중요★★★: 세션의 비밀번호 재설정
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		
		// 로그인 할 때 저장한 세션에 이전 비밀번호를 새로운 비밀번호로 업데이트
		memDTO.setMb_pw(dto.getMb_pw());
		session.setAttribute("user", memDTO);
		
		rttr.addFlashAttribute("msg", "PWCHANGE_SUCCESS");
		
		return "redirect:/";
	}
	
	// ▶ 회원 탈퇴(회원 수정 페이지(modify.jsp)에 탈퇴버튼)
	// String mb_id로 아이디 받을 시 → modify.js파일 참고
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(HttpSession session, RedirectAttributes rttr) throws Exception {
		
		logger.info("=====delete execute()");
		
		// 세션에 저장된 사용자아이디 가져오는 작업.
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		service.deleteUser(dto.getMb_id());
		
		// 탈퇴 시 세션 소멸작업
		session.invalidate();
		
		rttr.addFlashAttribute("msg", "DELETE_SUCCESS");
		
		return "redirect:/";
	}
}