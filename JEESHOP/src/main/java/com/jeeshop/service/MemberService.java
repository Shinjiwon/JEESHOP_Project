package com.jeeshop.service;

import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.MemberDTO;

public interface MemberService {

	// MemberVO 정보 가져오기
	public MemberVO readUserInfo(String mb_id) throws Exception;
	
	// 회원가입
	public void join(MemberVO vo) throws Exception;
	
	// 아이디 중복체크
	public String idDuplicateChk(String mb_id) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원정보 수정
	public void modifyUserInfo(MemberVO vo) throws Exception;
	
	// 비밀번호 변경
	public void pwChange(MemberDTO dto) throws Exception;
	
	// 회원 탈퇴
	public void deleteUser(String mb_id) throws Exception;
}
