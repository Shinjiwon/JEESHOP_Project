package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;

public interface ProductDAO {

	// 1차 카테고리 출력
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 2차 카테고리 출력
	public List<CategoryVO> subCateList(String cate_code) throws Exception;
	
	// 각 카테고리코드에 해당하는 카테고리명
	public String cateName(String cate_code) throws Exception;
	
	// 카테고리에 해당하는 상품리스트
	public List<ProductVO> proListCate(Map map) throws Exception;
	
	// 카테고리별 상품 개수
	public int proCount(String cate_code) throws Exception;
	
	// 상품 상세정보 읽어오기
	public ProductVO proRead(int pro_num) throws Exception;
}
