package com.jeeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdProductDAO;
import com.jeeshop.domain.CategoryVO;

@Service
public class AdProductServiceImpl implements AdProductService {
	
	@Autowired
	AdProductDAO dao;

	// 1차 카테고리
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		
		return dao.mainCateList();
	}

}
