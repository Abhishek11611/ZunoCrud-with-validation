package com.example.demo.service;

import com.example.demo.enums.Role;

public interface UserService {
	
	public String register(String username, String password, String email , Role userRole);

	public String login(String username, String password);
	
	public String changePassword(String username, String oldPassword, String newPassword);
}
