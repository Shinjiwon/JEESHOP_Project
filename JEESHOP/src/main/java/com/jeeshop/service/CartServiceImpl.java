package com.jeeshop.service;

import java.util.List;
import java.util.Map;

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
	
	// 장바구니 수량 변경
	@Override
	public void updateCart(Map map) throws Exception {

		dao.updateCart(map);
	}

	// 장바구니 삭제
	@Override
	public void deleteCart(int cat_code) throws Exception {

		dao.deleteCart(cat_code);
	}

	// 주문완료 후 장바구니 삭제
	@Override
	public void deleteCartOrder(Map map) throws Exception {

		dao.deleteCartOrder(map);
	}

}
