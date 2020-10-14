package com.jeeshop.service;

import com.jeeshop.dto.EmailDTO;

public interface EmailService {

	public void sendEmail(EmailDTO dto, String authcode) throws Exception;
}
