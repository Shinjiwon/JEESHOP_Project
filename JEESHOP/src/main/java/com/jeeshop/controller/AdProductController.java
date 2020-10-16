package com.jeeshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.domain.CategoryVO;
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
		
		// 1차 카테고리 리스트
		model.addAttribute("cateList", service.mainCateList());
	}
	
	// ▶ 1차 카테고리에 해당하는 2차 카테고리
	@ResponseBody
	@RequestMapping(value = "subCateList/{cate_code}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCateListPOST(@PathVariable("cate_code") String cate_code) {

		ResponseEntity<List<CategoryVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCateList(cate_code), HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
