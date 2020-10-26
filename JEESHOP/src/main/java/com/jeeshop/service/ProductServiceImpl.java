package com.jeeshop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jeeshop.dao.ProductDAO;
import com.jeeshop.domain.CategoryVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;

	// 1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		
		return dao.mainCateList();
	}

	// 2차 카테고리 출력
	@Override
	public List<CategoryVO> subCateList(String cate_code) throws Exception {

		return dao.subCateList(cate_code);
	}
}
