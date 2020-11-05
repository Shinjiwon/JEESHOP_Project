package com.jeeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeshop.dao.CartDAO;
import com.jeeshop.domain.CartProductVO;
import com.jeeshop.domain.CartVO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO dao;

	// 장바구니 상품추가
	@Override
	public void addCart(CartVO vo) throws Exception {

		dao.addCart(vo);
	}

	// 장바구니 정보 가져오기
	@Override
	public List<CartProductVO> getCart(String mb_id) throws Exception {

		return dao.getCart(mb_id);
	}
}
