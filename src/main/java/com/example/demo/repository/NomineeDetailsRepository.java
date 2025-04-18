package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NominieeDetailsEntity;

public interface NomineeDetailsRepository extends JpaRepository<NominieeDetailsEntity, Integer>{
	
	public List<NominieeDetailsEntity> getAllByPersonId(Integer personId);

}
