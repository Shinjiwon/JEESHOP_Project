package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.util.SearchCriteria;

public interface AdProductDAO {
	
	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 1차 카테고리에 해당하는 2차 카테고리 출력
	public List<CategoryVO> subCateList(String cate_code) throws Exception;
	
	// 상품 등록
	public void insertProduct(ProductVO vo) throws Exception;
	
	// 상품 리스트
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception;
	
	// 검색 조건에 맞는 상품 개수
	public int searchListCount(SearchCriteria cri) throws Exception;
	
	// 상품 정보 읽어오기
	public ProductVO proRead(int pro_num) throws Exception;
	
	// 상품 수정
	public void proEdit(ProductVO vo) throws Exception;
	
	// 체크된 상품 수정
	public void editCheck(Map<String, Object> map) throws Exception;
	
	// 상품 삭제
	public void proDelete(int pro_num) throws Exception;
}
