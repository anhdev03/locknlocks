package com.locknlock.locknlocks.locknlocks.model;

public class Email {
	private String emailAddress;
	private String emailSubject;
	private String emailBody;
	
	public Email(String emailAddress, String emailSubject, String emailBody) {
		super();
		this.emailAddress = emailAddress;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	
	
}
