package com.jeeshop.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.service.AdProductService;
import com.jeeshop.util.FileUtils;
import com.jeeshop.util.PageMaker;
import com.jeeshop.util.SearchCriteria;

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
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String proInsertPOST(ProductVO vo, RedirectAttributes rttr) throws Exception {
	
		logger.info("=====proInsertPOST execute()...");
		logger.info(vo.toString());
		
		vo.setPro_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		rttr.addFlashAttribute("msg", "PROINSERT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	// ▶ 상품 리스트
	/* url 이 처음 요청 받았을 경우  SearchCriteria cri 기본값을 가지게 됨. 
	   this.page = 1; 현재 페이지 번호. this.perPageNum = 10; // 페이지에 출력 게시물 개수
	   searchType = null,  keyword = null
	*/
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void proList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("=====proList execute()...");
		logger.info("=====cri: " + cri.toString());
		
		// 페이지 기능이 적용 된 상품 데이터
		model.addAttribute("productList", service.searchListProduct(cri));
		
		// PageMaker 생성
		PageMaker pm = new PageMaker(); // 1 2 3 4 5
		pm.setCri(cri); // 페이징 정보(page, perPageNum), 검색정보(searchType, keyword)
		
		// 테이블 전체 데이터 개수
		int count = service.searchListCount(cri);
		
		logger.info("=====일치하는 상품의 개수: " + count);
		pm.setTotalCount(count);
		
		// list.jsp에 1 2 3 4 5 링크 기능까지 작업 할 수 있는 정보
		model.addAttribute("pm", pm);
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
			
			logger.info("=====uploadPath: " + uploadPath);
			
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
	
	// ▶ 상품 상세정보 페이지
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void proRead(@ModelAttribute("cri") SearchCriteria cri,
						@RequestParam("pro_num") int pro_num, Model model) throws Exception {
		
		logger.info("=====proRead execute()...");
		
		ProductVO vo = service.proRead(pro_num);
		
		logger.info("=====Product Detail: " + vo.toString());
		
		model.addAttribute("vo", vo);
		
		// 상품 목록 클릭시 필요
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
	}
	
	// ▶ 상품 수정(GET)
	/*
	 * 상품 정보
	 * 1차 카테고리
	 * 선택된 2차 카테고리
	 * 저장되어있는 파일명
	 * pageMagker → 상품 정보, 목록에 필요
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void proEditGET(@ModelAttribute("cri") SearchCriteria cri,
						   @RequestParam("pro_num") int pro_num, Model model) throws Exception {
		logger.info("=====proEditGET execute");
		
		ProductVO vo = service.proRead(pro_num);
		
		logger.info("=====Product Detail: " + vo.toString());
		
		String originFile = vo.getPro_img().substring(vo.getPro_img().lastIndexOf("_") + 1);
		
		model.addAttribute("vo", vo);
		model.addAttribute("originFile", originFile);
		model.addAttribute("cateList", service.mainCateList());
		model.addAttribute("subCateList", service.subCateList(vo.getCate_prtcode()));
		
		// PageMaker → 상품목록 돌아가기 버튼 클릭 시 이동 목적
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
	}
	
	// ▶ 상품 수정(POST)
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String proEditPOST(ProductVO vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("=====proEditPOST execute()...");
		logger.info("=====Edit Info: " + vo.toString());
		logger.info("=====Cri Info: " + cri.toString());
		
		// 파일 사이즈로 새로운 파일 등록 여부 확인
		// 파일을 새로 등록하지 않은 경우, null값 또는 쓰레기 값이 넘어옴
		if(vo.getFile1().getSize() > 0) { // 파일이 변경된 경우
			// pro_img를 업로드 된 파일정보로 설정
			logger.info("=====File is not Zero size...");
			
			vo.setPro_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
			
		}
		logger.info("=====Changed Info: " + vo.toString());
		
		service.proEdit(vo);
		rttr.addFlashAttribute("cri", cri);
		rttr.addFlashAttribute("msg", "EDIT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	// ▶ 체크된 상품 수정
	@ResponseBody
	@RequestMapping(value = "editCheck", method = RequestMethod.POST)
	public ResponseEntity<String> proEditCheck(@RequestParam("checkArr[]") List<Integer> checkArr,
											   @RequestParam("amountArr[]") List<Integer> amountArr,
											   @RequestParam("buyArr[]") List<String> buyArr) {

		logger.info("=====proEditCheck execute()...");
		
		ResponseEntity<String> entity = null;
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			
			for(int i=0; i<checkArr.size(); i++) {
				map.put("pro_num", checkArr.get(i));
				map.put("pro_amount", amountArr.get(i));
				map.put("pro_buy", buyArr.get(i));
				
				service.editCheck(map);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		entity = new ResponseEntity<String>(HttpStatus.OK);
		
		return entity;
	}
	
	// ▶ 상품 삭제
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String proDeletePOST(SearchCriteria cri,
								@RequestParam("pro_num") int pro_num,
								@RequestParam("pro_img") String pro_img,
								RedirectAttributes rttr) throws Exception {
								
		logger.info("=====proDeletePOST execute()...");
		
		// 상품 이미지 삭제
		deleteFile(pro_img);
		
		// 상품 삭제
		service.proDelete(pro_num);
		
		rttr.addFlashAttribute("cri", cri);
		rttr.addFlashAttribute("msg", "DELETE_SUCCESS");

		return "redirect:/admin/product/list";
	}
	
	// ▶ 체크된 상품 삭제
	@RequestMapping(value = "deleteCheck", method = RequestMethod.POST)
	public ResponseEntity<String> proDeleteCheck(@RequestParam("checkArr[]") List<Integer> checkArr,
												 @RequestParam("imgArr[]") List<String> imgArr) {
		
		logger.info("=====proDeleteCheck execute()...");
		
		ResponseEntity<String> entity = null;
		
		try {
			for (int i=0; i<checkArr.size(); i++) {
				deleteFile(imgArr.get(i));
				service.proDelete(checkArr.get(i));
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
