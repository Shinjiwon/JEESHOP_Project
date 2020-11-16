package com.jeeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdminDAO;
import com.jeeshop.domain.AdminVO;
import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;

	// 관리자 로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {

		return dao.login(dto);
	}

	// 관리자 로그인 시간 업데이트
	@Override
	public void loginUpdate(String admin_id) throws Exception {

		dao.loginUpdate(admin_id);
	}

	// 회원목록
	@Override
	public List<MemberVO> UserInfoList() throws Exception {

		return dao.UserInfoList();
	}
	
}
