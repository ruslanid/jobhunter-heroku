package com.bazooka.jobhunter.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message="Email can't be blank")
	@Email(message="Email is invalid")
	private String username;
	
	@NotBlank(message="Password can't be blank")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
