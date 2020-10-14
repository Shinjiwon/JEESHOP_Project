package com.jeeshop.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jeeshop.dto.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Inject
	JavaMailSender mailSender; // root-context.xml에 bean 객체 생성해야 함.

	@Override
	public void sendEmail(EmailDTO dto, String authcode) throws Exception {
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			// 수신인 설정(이메일)
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getReceiveEmail()));
			// 발신인 설정
			msg.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderName(), dto.getSenderEmail())});
			// 메일 제목
			msg.setSubject(dto.getEmailTitle(), "utf-8");
			// 메일 내용
			msg.setText(dto.getMessage() + authcode, "utf-8");
			
			// 메일 전송
			mailSender.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}