package com.jeeshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeeshop.service.AdOrderService;
import com.jeeshop.util.Criteria;
import com.jeeshop.util.PageMaker;

@Controller
@RequestMapping("/admin/order/*")
public class AdOrderController {
	
	@Autowired
	private AdOrderService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdOrderController.class);

	// ▶ 주문목록 페이지
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void orderList(@ModelAttribute("cri") Criteria cri,
							Model model) throws Exception {
		
		logger.info("=====orderList execute()...");
		
		model.addAttribute("orderList", service.orderList());
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.orderCount();
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
}
