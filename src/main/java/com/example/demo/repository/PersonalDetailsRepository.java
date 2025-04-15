package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PersonalDetailsEntity;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetailsEntity, Integer> {

	 @Query("SELECT p FROM PersonalDetailsEntity p WHERE p.status = :status")
	    List<PersonalDetailsEntity> findByStatus(@Param("status") String status);
	 
	  public Optional<PersonalDetailsEntity> findByPersonIdAndStatus(Integer personId, String status);
	
}
