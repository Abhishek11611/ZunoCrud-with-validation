package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserToken;
import com.example.demo.enums.Role;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserTokenRepository;

@Service
public class UserServiceImpl implements UserService{

   
	@Autowired
    private UserRepository userRepo; 
	
	@Autowired
	private UserTokenRepository userTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private JwtUtil jwtUtil;
   
    public String register(String username, String password, String email , Role userRole) {
        if (username == null || password == null|| userRole ==null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setUserRole(userRole);
        userRepo.save(user);

        return "User registered successfully!";
    }


    public String login(String username, String password) {
      
    	User user = userRepo.findByUsername(username)
    	        .orElseThrow(() -> new UsernameNotFoundException(" User not found"));
    	
    	authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(username, password)
    			);
 
    	    Map<String, Object> extraClaims = new HashMap<>();
    	    extraClaims.put("userId", user.getId());

    	    final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
    	        username, password, new ArrayList<>()
    	    );
    	    
    	    Optional<UserToken> tokenOptional = userTokenRepository.findByUsername(username);

            if (tokenOptional.isPresent()) {
                UserToken existingToken = tokenOptional.get();
                if (existingToken.getExpiryDate().after(new java.util.Date())) {
                    
                    return existingToken.getToken();
                }
            }
    	    
    	    return jwtUtil.generateToken(userDetails, extraClaims);

}


	@Override
	public String changePassword(String username, String oldPassword, String newPassword) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, oldPassword)
				);
		 User user = userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		 String encode = passwordEncoder.encode(newPassword);
		 user.setPassword(encode);
		 userRepo.save(user);
		
		return "Password Changed SuccessFully!!";
	}
    }