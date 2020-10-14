package com.jeeshop.dto;

public class EmailDTO {
	
	private String senderName; // 발신자 이름
	private String senderEmail; // 발신자 이메일
	private String receiveEmail; // 수신자 이메일
	private String emailTitle; // 메일 제목
	private String message; // 내용
	
	public EmailDTO() {
		this.senderName = "JEESHOP";
		this.senderEmail = "JEESHOP";
		this.emailTitle = "JEESHOP 이메일 인증코드";
		this.message = "이메일 인증코드란에 코드를 입력해주세요. \n인증코드: ";
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getReceiveEmail() {
		return receiveEmail;
	}
	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}
	public String getEmailTitle() {
		return emailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "EmailDTO [senderName=" + senderName + ", senderEmail=" + senderEmail + ", receiveEmail=" + receiveEmail
				+ ", emailTitle=" + emailTitle + ", message=" + message + "]";
	}
}