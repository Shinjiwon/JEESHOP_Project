package com.jeeshop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.dto.AdminDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession session;
	public final static String NS = "com.jeeshop.mappers.AdminMapper";
	
	// 관리자 로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {

		return session.selectOne(NS+".login", dto);
	}

	// 관리자 로그인 시간 업데이트
	@Override
	public void loginUpdate(String admin_id) throws Exception {

		session.update(NS+".loginUpdate", admin_id);
	}
}
