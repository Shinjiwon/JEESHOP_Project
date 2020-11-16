package com.jeeshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jeeshop.dto.MemberDTO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/* preHandler: Controller보다 먼저 수행
	 
	 * @return
	   → true: 컨트롤러 동작 진행
	   → false: 컨트롤러 동작을 실행하지 않음 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		MemberDTO user = (MemberDTO) session.getAttribute("user");
		
		if (user == null) {
			logger.info("=====로그인 정보가 없습니다. 로그인 페이지로 이동합니다.");
			response.sendRedirect("/member/login");
			return false;
			
		}
		return true;
	}
}