package com.jeeshop.service;

import java.util.List;

import com.jeeshop.domain.CategoryVO;

public interface AdProductService {

	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
	
	// 1차 카테고리에 해당하는 2차 카테고리 출력
	public List<CategoryVO> subCateList(String cate_code) throws Exception;
}
