package com.jeeshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeeshop.service.AdOrderService;

@Controller
@RequestMapping("/admin/order/*")
public class AdOrderController {
	
	@Autowired
	private AdOrderService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdOrderController.class);

	// ▶ 주문목록 페이지
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void orderList(Model model) throws Exception {
		
		logger.info("=====orderList execute()...");
		
		model.addAttribute("orderList", service.orderList());
	}
}
