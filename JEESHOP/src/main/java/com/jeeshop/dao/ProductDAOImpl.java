package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.CategoryVO;
import com.jeeshop.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession session;
	
	private final static String NS = "com.jeeshop.mappers.ProductMapper";

	// 1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCateList() throws Exception {

		return session.selectList(NS+".mainCateList");
	}

	// 2차 카테고리 출력
	@Override
	public List<CategoryVO> subCateList(String cate_code) throws Exception {

		return session.selectList(NS+".subCateList", cate_code);
	}

	// 각 카테고리코드에 해당하는 카테고리명
	@Override
	public String cateName(String cate_code) throws Exception {

		return session.selectOne(NS+".cateName", cate_code);
	}

	// 카테고리에 해당하는 상품리스트
	@Override
	public List<ProductVO> proListCate(Map map) throws Exception {
		
		return session.selectList(NS+".proListCate", map);
	}

	// 카테고리별 상품 개수
	@Override
	public int proCount(String cate_code) throws Exception {
		
		return session.selectOne(NS+".proCount", cate_code);
	}

	// 상품 상세정보 읽어오기
	@Override
	public ProductVO proRead(int pro_num) throws Exception {

		return session.selectOne(NS+".proRead", pro_num);
	}
	
	
}
