package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PersonalDetailsEntity;

@Repository
public interface PersonalDetailsRepository extends JpaRepository<PersonalDetailsEntity, Integer> {

//	 @Query("SELECT p FROM PersonalDetailsEntity p WHERE p.status = :status")
//	  public  List<PersonalDetailsEntity> findByStatus(@Param("status") String status);
	
	@Query("SELECT p FROM PersonalDetailsEntity p WHERE p.personId = :personId AND p.status = :status")
	public Optional<PersonalDetailsEntity> findByPersonIdAndStatuse(@Param("personId") Integer personId, @Param("status") String status);


//	 public  List<PersonalDetailsEntity> findByStatus(String status,Pageable pageable);

//	 public Page<PersonalDetailsEntity> findByStatus(String status, Pageable pageable);
	 
	public List<PersonalDetailsEntity> findByStatus(String status);

	public Optional<PersonalDetailsEntity> findByPersonIdAndStatus(Integer personId, String status);

	public boolean existsByPersonMobileNo(Long personMobileNo);

	public boolean existsByPersonEmail(String personEmail);

	public boolean existsByPersonAadhaarNumber(Long personAadhaarNumber);

	public boolean existsByPersonPanNumber(String personPanNumber);
	
	
	   
	
}
