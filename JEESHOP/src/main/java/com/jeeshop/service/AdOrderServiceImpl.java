package com.jeeshop.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdOrderDAO;
import com.jeeshop.domain.OrderVO;

@Service
public class AdOrderServiceImpl implements AdOrderService {

	@Inject
	private AdOrderDAO dao;

	// 주문목록 가져오기
	@Override
	public List<OrderVO> orderList() throws Exception {
		
		return dao.orderList();
	}
}
