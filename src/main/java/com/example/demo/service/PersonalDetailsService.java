package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PersonalDetailsEntity;

public interface PersonalDetailsService {
	
	public PersonalDetailsEntity addPerson(PersonalDetailsEntity personalDetailsEntity);
	
	public List<PersonalDetailsEntity> getAllPersonDetails();
	
	public String deletePersonDetails(Integer personId);
	
	public String updatepersonById(Integer personId,PersonalDetailsEntity personalDetailsEntity);

}
