package com.example.demo.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTokenRepository userTokenRepository;

    // Secure key - must be at least 32 characters for HS256
    private static final String SECRET = "my-super-secret-key-that-is-strong-123!";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
    	
    	
         String token = Jwts
        	.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername()) 
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
        
        saveTokenToDatabase(userDetails.getUsername(),token);
        
        return token;
    }
    
    public void saveTokenToDatabase(String username, String token) {
    	
    	boolean existsByUsername = userTokenRepository.existsByUsername(username);
    	
    	if(existsByUsername) {
    		Optional<UserToken> byUsername = userTokenRepository.findByUsername(username);
    		UserToken userToken = byUsername.get();
    		userToken.setToken(token);
    		
    		userTokenRepository.save(userToken);
		} else {

			UserToken userToken = new UserToken();
			userToken.setUsername(username);
			userToken.setToken(token);
			userToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 5));

			userTokenRepository.save(userToken);
		}
    	
    	
    }
    

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
//        final String email = extractEmail(token);
        
        
//        Optional<User> userentity = userRepository.findByEmail(email);
//        User user1 = userentity.get();
        
        if(!username.equals(userDetails.getUsername()) && !isTokenExpired(token)) {
        	throw new IllegalArgumentException("Your Token is Invalid");
        }
        
//        if(!email.equals(user1.getEmail()) && !isTokenExpired(token)) {
//        	return false;
//        }
        
        return true;
    }
}
