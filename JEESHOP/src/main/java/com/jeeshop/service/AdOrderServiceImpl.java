package com.jeeshop.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jeeshop.dao.AdOrderDAO;

@Service
public class AdOrderServiceImpl implements AdOrderService {

	@Inject
	private AdOrderDAO dao;
}
