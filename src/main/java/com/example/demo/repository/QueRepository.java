package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.QueTable;

public interface QueRepository extends JpaRepository<QueTable,Integer> {
	
	List<QueTable> findByIsProcess(String isProcess);

}
