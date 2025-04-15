package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.requestdto.PdRequestDto;

public interface PersonalDetailsService {
	
	public String addPerson(PdRequestDto pdRequestDto);
	
	public List<PdRequestDto> getAllPersonDetails();
	
	public String deletePersonDetails(Integer personId);
	
	public String updatepersonById(Integer personId,PdRequestDto pdRequestDto);
	
	public String findbyidPersonDetails(Integer personId);

}
