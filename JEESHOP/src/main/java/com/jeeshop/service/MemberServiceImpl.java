package com.jeeshop.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.MemberDAO;
import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Inject
	private BCryptPasswordEncoder crptPassEnc;
	
	// MemberVO 정보 가져오기
	@Override
	public MemberVO readUserInfo(String mb_id) throws Exception {
		
		return dao.readUserInfo(mb_id);
	}

	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		
		dao.join(vo);
	}

	// 아이디 중복체크
	@Override
	public String idDuplicateChk(String mb_id) throws Exception {
		
		return dao.idDuplicateChk(mb_id);
	}

	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {

		MemberDTO memDTO = dao.login(dto);
		
		if(memDTO != null) {
			
			if(crptPassEnc.matches(dto.getMb_pw(), memDTO.getMb_pw())) {
				dao.loginUpdate(memDTO.getMb_id());
				
			} else {
				memDTO = null;
			}
		}
		
		return memDTO;
	}

	// 회원정보 수정
	@Override
	public void modifyUserInfo(MemberVO vo) throws Exception {
		
		dao.modifyUserInfo(vo);
	}

	// 비밀번호 변경
	@Override
	public void pwChange(MemberDTO dto) throws Exception {
		
		dao.pwChange(dto);
	}

	// 회원 탈퇴
	@Override
	public void deleteUser(String mb_id) throws Exception {

		dao.deleteUser(mb_id);
	}
}
