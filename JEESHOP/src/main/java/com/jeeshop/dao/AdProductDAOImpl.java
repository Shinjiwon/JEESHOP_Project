package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;
import com.jeeshop.util.SearchCriteria;

@Repository
public class AdProductDAOImpl implements AdProductDAO {
	
	@Autowired
	SqlSession session;
	public final static String NS = "com.jeeshop.mappers.AdProductMapper";

	// 1차 카테고리
	@Override
	public List<CategoryVO> mainCateList() throws Exception {
		
		return session.selectList(NS+".mainCateList");
	}

	// 1차 카테고리에 해당하는 2차 카테고리 출력
	@Override
	public List<CategoryVO> subCateList(String cate_code) throws Exception {
		
		return session.selectList(NS+".subCateList", cate_code);
	}

	// 상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		
		session.insert(NS+".insertProduct", vo);
	}

	// 상품 리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {

		return session.selectList(NS+".searchListProduct", cri);
	}

	// 검색 조건에 맞는 상품 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		
		return session.selectOne(NS+".searchListCount", cri);
	}
	
	// 체크된 상품 수정
	@Override
	public void editCheck(Map<String, Object> map) throws Exception {
		
		session.update(NS+".editCheck", map);
	}

	// 상품 삭제
	@Override
	public void proDelete(int pro_num) throws Exception {
		
		session.delete(NS+".proDelete", pro_num);
	}
}
