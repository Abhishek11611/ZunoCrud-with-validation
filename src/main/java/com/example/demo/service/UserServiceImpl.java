package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

   
	@Autowired
    private UserRepository userRepo; 

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private JwtUtil jwtUtil;
   
    public String register(String username, String password, String email) {
        if (username == null || password == null || email == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        userRepo.save(user);

        return "User registered successfully!";
    }


    public String login(String username, String password) {
      
    	User user = userRepo.findByUsername(username)
    	        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	
    	authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(username, password)
    			);

    	    // Custom claims 
    	    Map<String, Object> extraClaims = new HashMap<>();
    	    extraClaims.put("userId", user.getId());
    	    extraClaims.put("email", user.getEmail()); 

    	    // JWT token generate kiya with extra claims
    	    final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
    	        username, password, new ArrayList<>()
    	    );
    	    return jwtUtil.generateToken(userDetails, extraClaims);

}
    }