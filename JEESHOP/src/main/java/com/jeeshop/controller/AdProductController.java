package com.jeeshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeeshop.service.AdProductService;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {
	
	@Autowired
	AdProductService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdProductController.class);
	
	// ▶ 상품 등록 페이지
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void proInsertGet(Model model) throws Exception {
		
		logger.info("=====insertGet execute()...");
		
		model.addAttribute("cateList", service.mainCateList());
	}
}
