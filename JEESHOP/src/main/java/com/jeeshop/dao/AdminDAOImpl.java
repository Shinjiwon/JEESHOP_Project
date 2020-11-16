package com.jeeshop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.domain.MemberVO;
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

	// 회원목록
	@Override
	public List<MemberVO> UserInfoList() throws Exception {
		
		return session.selectList(NS+".UserInfoList");
	}

	// 회원 수 가져오기
	@Override
	public int userCount() throws Exception {
		
		return session.selectOne(NS+".userCount");
	}
}
