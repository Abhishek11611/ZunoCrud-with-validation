package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.service.PersonalDetailsService;

@RestController
@RequestMapping("/personaldetails-api")
public class PersonalDetailsController {
	
	@Autowired
	private PersonalDetailsService personalDetailsService;
	
	@PostMapping("/add-person")
	public ResponseEntity<PersonalDetailsEntity>addperson(@RequestBody PersonalDetailsEntity personalDetailsEntity){
		PersonalDetailsEntity per=personalDetailsService.addPerson(personalDetailsEntity);
		return new ResponseEntity<PersonalDetailsEntity>(per,HttpStatus.CREATED);
	}
	
	@GetMapping("/getall-person")
	public ResponseEntity<List<PersonalDetailsEntity>>getAllPersonDetails(){
	List<PersonalDetailsEntity> per =	personalDetailsService.getAllPersonDetails();
		return new ResponseEntity<List<PersonalDetailsEntity>>(per,HttpStatus.ACCEPTED);
	}
	
//	@DeleteMapping("/delete-person/{personId}")
	
	@PatchMapping("/delete-person/{personId}")
	public ResponseEntity<String>deletePersonDetails(@PathVariable Integer personId){
		String perondelete = personalDetailsService.deletePersonDetails(personId);
		return new ResponseEntity<String>(perondelete,HttpStatus.ACCEPTED);
	}
   
	@PutMapping("/update-person/{personId}")
	public ResponseEntity<String>updatepersonById(@PathVariable Integer personId,@RequestBody PersonalDetailsEntity detailsEntity){
		String personupdate = personalDetailsService.updatepersonById(personId, detailsEntity);
		return new ResponseEntity<String>(personupdate,HttpStatus.ACCEPTED);
		
	}
}
