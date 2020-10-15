package com.jeeshop.dao;

import java.util.List;

import com.jeeshop.domain.CategoryVO;

public interface AdProductDAO {
	
	// 1차 카테고리
	public List<CategoryVO> mainCateList() throws Exception;

}
