package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

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
	
	// 장바구니 수량 변경
	@Override
	public void updateCart(Map map) throws Exception {

		session.update(NS+".updateCart", map);
	}

	// 장바구니 삭제
	@Override
	public void deleteCart(int cat_code) throws Exception {

		session.delete(NS+".deleteCart", cat_code);
	}

	// 주문완료 후 장바구니 삭제
	@Override
	public void deleteCartOrder(Map map) throws Exception {

		session.delete(NS+".deleteCartOrder", map);
	}

}
