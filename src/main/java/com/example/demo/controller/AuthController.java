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
import com.example.demo.requestdto.ChangePasswordRequest;
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
			String response1 = userService.register(request.getUsername(), request.getPassword(), request.getEmail(),request.getUserRole());
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
			String sessionid = userService.login(request.getUsername(), request.getPassword());
			response.setStatus(true);
			response.setMessage(" successfully");
			response.setData("SessionId = " + sessionid);

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}

		return response;

	}
	
	@PostMapping("/reset_password")
	public ResponseHandler changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		ResponseHandler response = new ResponseHandler();

		try {
			 String changePassword = userService.changePassword(changePasswordRequest.getUsername(), changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
			response.setStatus(true);
			response.setMessage(" successfully");
			response.setData(changePassword);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed" + e.getMessage());
			response.setData(null);
		}
		return response;

	}

}