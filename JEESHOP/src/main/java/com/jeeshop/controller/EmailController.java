package com.jeeshop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.dto.EmailDTO;
import com.jeeshop.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	
	@Inject
	private EmailService emailService;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@ResponseBody
	@RequestMapping("sendEmail")
	public ResponseEntity<String> sendEmail(EmailDTO dto, HttpSession session) {
		
		logger.info("=====sendEmail execute()...");
		logger.info("=====EmailDTO: " + dto.toString());
		
		ResponseEntity<String> entity = null;
		
		// 인증번호 생성(6자리)
		String authcode = "";
		for(int i=0; i<6; i++ ) {
			authcode += String.valueOf((int)(Math.random()*10));
		}
		
		// 세션에 인증코드 저장
		session.setAttribute("authcode", authcode);
		
		logger.info("=====authcode: " + authcode);
		
		try {
			emailService.sendEmail(dto, authcode);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
