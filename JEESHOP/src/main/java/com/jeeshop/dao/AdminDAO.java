package com.jeeshop.dao;

import java.util.List;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.AdminDTO;

public interface AdminDAO {
	
	// 관리자 로그인
	public AdminVO login(AdminDTO dto) throws Exception;
	
	// 관리자 로그인 시간 업데이트
	public void loginUpdate(String admin_id) throws Exception;
	
	// 회원목록
	public List<MemberVO> UserInfoList() throws Exception;
}
