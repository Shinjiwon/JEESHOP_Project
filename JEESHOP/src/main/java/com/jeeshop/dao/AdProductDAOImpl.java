package com.jeeshop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.CategoryVO;

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
}
