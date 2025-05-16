package com.example.demo.service;

public interface UserService {
	
	public String register(String username, String password, String email);

	public String login(String username, String password);
}
