package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.requestdto.AuthenticationRequest;
import com.example.demo.requestdto.AuthenticationResponse;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/register")
	public ResponseHandler register(@RequestBody AuthenticationRequest request) {
		ResponseHandler response = new ResponseHandler();

		try {
			String response1 = userService.register(request.getUsername(), request.getPassword(), request.getEmail());
			response.setStatus(true);
			response.setMessage(" successfully");
			response.setData(response1);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}
		return response;

	}

	@PostMapping("/login")
	public ResponseHandler login(@RequestBody AuthenticationRequest request) {

		ResponseHandler response = new ResponseHandler();

		try {
			String username = request.getUsername();
			String password = request.getPassword();
			String token = userService.login(username, password);
			AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);

			response.setStatus(true);
			response.setMessage(" successfully");
			response.setData(authenticationResponse);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}
		return response;

	}
}