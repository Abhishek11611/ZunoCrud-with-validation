package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;

public interface PersonalDetailsService {
	
	public String addPerson(PdRequestDto pdRequestDto);
	
	public List<PdRequiredDto> getAllPersonDetails( Integer pageNumber, Integer pageSize);
	
	public String deletePersonDetails(Integer personId);
	
	public String updatepersonById(Integer personId,PdRequestDto pdRequestDto);
	
	public PdRequestDto findbyidPersonDetails(Integer personId);

}
