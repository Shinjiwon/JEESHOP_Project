package com.jeeshop.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdOrderDAOImpl implements AdOrderDAO {

	@Inject
	private SqlSession session;
	
	public final static String NS = "com.jeeshop.mappers.AdOrderMapper";
}
