package com.jeeshop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.CategoryVO;

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
}
