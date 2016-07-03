package com.example.mbean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class LoginView {

	private String userId;
	private String userName;
	private String password;
	private String sessionId = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	
	@PostConstruct
	public void initBean()
	{
		System.out.println("Initializing Bean"+this.getClass().toString());
	}
	
	@PreDestroy
	public void destroyBean()
	{
		System.out.println("Destroying Bean"+this.getClass().toString());
	}
}
