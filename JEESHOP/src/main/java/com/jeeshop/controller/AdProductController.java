package com.jeeshop.controller;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;

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
	/*
	 ARC프로그램으로 주소 테스트 →  "/admin/product/subCGList/" + mainCGCode
	 
	 @PathVariable: 주소가 경로로 들어왔을 때 일부에 해당하는 것을 값으로 사용할 때
	                                리턴값을 json으로 사용하기 위해서는 필수 라이브러리 사용
	                                
	 @PathVariable("cate_code"): Path를 통해 들어온 1차 카테고리
	 */
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
	
	// ▶ CkEditor를 통한 상품상세 이미지 업로드
	public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		logger.info("imgUpload execute()...");
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 1)클라이언트로 보내기 위한 정보 설정
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			// 전송 할 파일 가져오기
			String fileName = upload.getOriginalFilename();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
