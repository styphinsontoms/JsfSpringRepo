package com.example.dao.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String languageCode;
	private String password;
	private String userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
