package com.jeeshop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.MemberVO;
import com.jeeshop.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;
	public final static String NS = "com.jeeshop.mappers.MemberMapper";
	
	// MemberVO 정보 가져오기
	@Override
	public MemberVO readUserInfo(String mb_id) throws Exception {
		
		return session.selectOne(NS+".readUserInfo", mb_id);
	}

	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		
		session.insert(NS+".join", vo);
	}

	// 아이디 중복체크
	@Override
	public String idDuplicateChk(String mb_id) throws Exception {
		
		return session.selectOne(NS+".idDuplicateChk", mb_id);
	}

	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {

		return session.selectOne(NS+".login", dto);
	}
	
	// 로그인 시간 업데이트
	@Override
	public void loginUpdate(String mb_id) throws Exception {
		
		session.update(NS+".loginUpdate", mb_id);
	}

	// 회원정보 수정
	@Override
	public void modifyUserInfo(MemberVO vo) throws Exception {
		
		session.update(NS+".modifyUserInfo", vo);
	}

	// 비밀번호 변경
	@Override
	public void pwChange(MemberDTO dto) throws Exception {
		
		session.update(NS+".pwChange", dto);
	}

	// 회원 탈퇴
	@Override
	public void deleteUser(String mb_id) throws Exception {

		session.delete(NS+".deleteUser", mb_id);
	}
}
