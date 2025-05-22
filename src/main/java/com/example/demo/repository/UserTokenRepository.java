package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Integer> {
	
	Optional<UserToken> findByUsername (String username);

}
