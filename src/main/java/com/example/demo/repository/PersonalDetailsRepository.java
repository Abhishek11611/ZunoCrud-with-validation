package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PersonalDetailsEntity;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetailsEntity, Integer> {

//	 @Query("SELECT p FROM PersonalDetailsEntity p WHERE p.status = :status")
//	  public  List<PersonalDetailsEntity> findByStatus(@Param("status") String status);
	
//	public  List<PersonalDetailsEntity> findByStatus(String status,Pageable pageable);
	
	 public Page<PersonalDetailsEntity> findByStatus(String status, Pageable pageable);
	 
	  public Optional<PersonalDetailsEntity> findByPersonIdAndStatus(Integer personId, String status);
	  
	   public  boolean existsByPersonMobileNo(Long personMobileNo);
	   
	   public boolean existsByPersonEmail(String personEmail );
	   
	   public boolean existsByPersonAadhaarNumber(Long personAadhaarNumber);
	   
	   public boolean existsByPersonPanNumber(String personPanNumber);  
	    
	   
	
}
