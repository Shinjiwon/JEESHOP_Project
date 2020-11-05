package com.jeeshop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeeshop.domain.CartProductVO;
import com.jeeshop.domain.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	SqlSession session;
	
	public final static String NS = "com.jeeshop.mappers.CartMapper";

	// 장바구니 상품추가
	@Override
	public void addCart(CartVO vo) throws Exception {
		
		session.insert(NS+".addCart", vo);
	}

	// 장바구니 정보 가져오기
	@Override
	public List<CartProductVO> getCart(String mb_id) throws Exception {
		
		return session.selectList(NS+".getCart", mb_id);
	}
}
