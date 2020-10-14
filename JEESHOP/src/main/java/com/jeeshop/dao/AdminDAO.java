package com.jeeshop.dao;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.dto.AdminDTO;

public interface AdminDAO {
	
	// 관리자 로그인
	public AdminVO login(AdminDTO dto) throws Exception;
}
