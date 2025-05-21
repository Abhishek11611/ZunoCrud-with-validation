package com.example.demo.requestdto;

import com.example.demo.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AuthenticationRequest {
	private String username;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role userRole;

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
