package com.jeeshop.service;

import java.util.List;

import com.jeeshop.domain.CategoryVO;

public interface AdProductService {

	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;
}
