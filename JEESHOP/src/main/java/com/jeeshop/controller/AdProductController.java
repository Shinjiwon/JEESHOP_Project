package com.jeeshop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.service.AdProductService;
import com.jeeshop.util.FileUtils;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {
	
	@Autowired
	AdProductService service;
	
	// 웹프로젝트영역 외부에 파일을 저장할 경로. servlet-context.xml에서 설정
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(AdProductController.class);
	
	// ▶ 상품 등록 페이지
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public void proInsertGet(Model model) throws Exception {
		
		logger.info("=====insertGet execute()...");
		
		// 1차 카테고리 리스트
		model.addAttribute("cateList", service.mainCateList());
	}
	
	// ▶ 상품 등록(POST)
	public String proInsertPOST(ProductVO vo, RedirectAttributes rttr) throws Exception {
	
		logger.info("=====proInsertPOST execute()...");
		logger.info(vo.toString());
		
		vo.setPro_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		rttr.addFlashAttribute("msg", "INSERT_SUCCESS");
		
		return null;
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
	@RequestMapping(value = "imgUpload", method = RequestMethod.POST)
	public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		logger.info("imgUpload execute()...");
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		// 1)클라이언트로 보내기 위한 정보 설정
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			// 전송 할 파일 정보 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			// 톰캣이 현재 프로젝트 대신 임시로 사용하기 위한 폴더정보 참조
			// D:\workspace\spring_work\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JEESHOP
			String uploadPath = req.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			
			logger.info("uploadPath: " + uploadPath);
			
			// 출력 스트림 생성
			out = new FileOutputStream(new File(uploadPath));
			// 파일 쓰기
			out.write(bytes);
			
			// 2)클라이언트로 보내기 위한 정보 설정
			// <resources mapping="/upload/**" location="/resources/upload/" />
			printWriter = res.getWriter();
			String fileUrl = "/upload/" + fileName;
			
			// 업로드된 파일의 정보를 CkEditor로 보내는 기능. 자바스크립트 객체구문 문법(JSON)
			printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (out != null) {
				// 출력 스트림 종료
				try {out.close();} catch (Exception e) {e.printStackTrace();}
				
			}
			if (printWriter != null) {
				// printWriter 종료
				try {printWriter.close();} catch (Exception e) {e.printStackTrace();}
			}
		}
	}
	
	// ▶ 저장된 파일을 가져와 섬네일 이미지에 출력. 저장된 파일을 가져와 반환
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFiles(uploadPath, fileName);
	}
	
	// ▶ 이미지 파일 삭제 → 선택된 상품 삭제할 때 사용되는 메서드
	public void deleteFile(String fileName) {
		
		FileUtils.deleteFile(uploadPath, fileName);
	}
}
