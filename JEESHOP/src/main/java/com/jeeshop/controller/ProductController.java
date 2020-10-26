package com.jeeshop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	ProductService service;
	
	// 웹프로젝트영역 외부에 파일을 저장할 경로. servlet-context.xml에서 설정
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	// ▶ 1차 카테고리에 따른 2차 카테고리 출력
	/*
	 * @Params
	 * @PathVariable("cate_code") → path로 들어온 1차 카테고리
	  
	 * @return ResponseEntity<List<CategoryVO>>
	    → REST방식에 따른 HttpStatus 상태 같이 전송
	 */
	@ResponseBody
	@RequestMapping(value = "subCateList/{cate_code}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCateList(@PathVariable("cate_code") String cate_code) {
		
		logger.info("=====subCateList execute()...");
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		try {
			logger.info("=====" + service.subCateList(cate_code));
			entity = new ResponseEntity<List<CategoryVO>>(service.subCateList(cate_code), HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
}
