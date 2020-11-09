package com.jeeshop.dao;

import java.util.List;
import java.util.Map;

import com.jeeshop.domain.CartProductVO;
import com.jeeshop.domain.CartVO;

public interface CartDAO {

	// 장바구니 상품추가
	public void addCart(CartVO vo) throws Exception;
	
	// 장바구니 정보 가져오기
	public List<CartProductVO> getCart(String mb_id) throws Exception;
	
	// 장바구니 수량 변경
	public void updateCart(Map map) throws Exception;
	
	// 장바구니 삭제
	public void deleteCart(int cat_code) throws Exception;
	
	// 주문완료 후 장바구니 삭제
	public void deleteCartOrder(Map map) throws Exception;
}
