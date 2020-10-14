package com.jeeshop.service;

import com.jeeshop.domain.AdminVO;
import com.jeeshop.dto.AdminDTO;

public interface AdminService {

	// 관리자 로그인
	public AdminVO login(AdminDTO dto) throws Exception;
}
