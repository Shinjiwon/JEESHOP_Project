package com.jeeshop.service;

import java.util.List;

import com.jeeshop.domain.CartProductVO;
import com.jeeshop.domain.CartVO;

public interface CartService {

	// 장바구니 상품추가
	public void addCart(CartVO vo) throws Exception;
	
	// 장바구니 정보 가져오기
	public List<CartProductVO> getCart(String mb_id) throws Exception;
}
