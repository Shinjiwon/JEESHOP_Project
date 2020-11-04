package com.jeeshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.service.ProductService;
import com.jeeshop.service.ReviewService;
import com.jeeshop.util.Criteria;
import com.jeeshop.util.FileUtils;
import com.jeeshop.util.PageMaker;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ReviewService reviewService;
	
	// 웹프로젝트영역 외부에 파일을 저장할 경로. servlet-context.xml에서 설정
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	// ▶ 1차 카테고리에 따른 2차 카테고리 출력, 1차 카테고리 출력은 GlobalControllerAdvice
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
	
	// ▶ 썸네일 이미지 출력
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		return FileUtils.getFiles(uploadPath, fileName);
	}
	
	// ▶ 카테고리에 따른 상품 리스트 출력
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") Criteria cri,
			         @ModelAttribute("cate_code") String cate_code,
			         Model model) throws Exception {
		
		logger.info("=====list execute()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate_code", cate_code);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.proListCate(map);
		model.addAttribute("proList", list);
		model.addAttribute("cate_name", service.cateName(cate_code));
		
		// PageMaker
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.proCount(cate_code);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	// ▶  상품 상세정보 읽어오기
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void proReadGET(@ModelAttribute("cri") Criteria cri,
						   @RequestParam("pro_num") int pro_num, Model model) throws Exception {
		
		logger.info("=====proReadGET execute()...");
		
		ProductVO vo = service.proRead(pro_num);
		vo.setPro_img(FileUtils.thumbToOriginName(vo.getPro_img()));
		
		logger.info("=====Product Detail: " + vo.toString());
		
		model.addAttribute("vo", vo);
		
		// 상품 목록으로 돌아가기 위한 PageMaker 생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
		// 해당 상품의 리뷰 개수
		model.addAttribute("totalReview", reviewService.reviewCount(vo.getPro_num()));
	}
}
