package com.jeeshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdProductDAO;
import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.util.SearchCriteria;

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

	// 상품 리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {

		return dao.searchListProduct(cri);
	}

	// 검색 조건에 맞는 상품 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		
		return dao.searchListCount(cri);
	}
	
	// 상품 정보 읽어오기
	@Override
	public ProductVO proRead(int pro_num) throws Exception {

		return dao.proRead(pro_num);
	}
	
	// 상품 수정
	@Override
	public void proEdit(ProductVO vo) throws Exception {

		dao.proEdit(vo);
	}
	
	// 체크된 상품 수정
	@Override
	public void editCheck(Map<String, Object> map) throws Exception {

		dao.editCheck(map);
	}

	// 상품 삭제
	@Override
	public void proDelete(int pro_num) throws Exception {

		dao.proDelete(pro_num);
	}

}
