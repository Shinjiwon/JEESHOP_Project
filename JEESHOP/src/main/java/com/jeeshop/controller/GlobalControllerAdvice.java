package com.jeeshop.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jeeshop.service.ProductService;

@ControllerAdvice(basePackages = {"com.jeeshop.controller"})
public class GlobalControllerAdvice {

	@Inject
	private ProductService service;
	
	// ▶ 1차 카테고리 출력
	@ModelAttribute
	public void mainCateList(Model model) throws Exception {
		 
		model.addAttribute("userCateList", service.mainCateList());
	}
}
