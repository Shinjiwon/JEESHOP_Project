package com.jeeshop.dao;

import java.util.List;

import com.jeeshop.domain.CategoryVO;

public interface ProductDAO {

	// 1차 카테고리 출력
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 2차 카테고리 출력
	public List<CategoryVO> subCateList(String cate_code) throws Exception;
}
