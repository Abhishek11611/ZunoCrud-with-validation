package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NominieeDetailsEntity;

public interface NomineeDetailsRepository extends JpaRepository<NominieeDetailsEntity, Integer>{
	
	public List<NominieeDetailsEntity> getAllByPersonId(Integer personId);
	
	public Optional<NominieeDetailsEntity> findAllByPersonId(Integer personId);
}
