package com.jeeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdProductDAO;
import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;

@Service
public class AdProductServiceImpl implements AdProductService {
	
	@Autowired
	AdProductDAO dao;

	// 1차 카테고리
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		
		return dao.mainCateList();
	}

	// 1차 카테고리에 해당하는 2차 카테고리 출력
	@Override
	public List<CategoryVO> subCateList(String cate_code) throws Exception {
		
		return dao.subCateList(cate_code);
	}

	// 상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {

		dao.insertProduct(vo);
	}

}
