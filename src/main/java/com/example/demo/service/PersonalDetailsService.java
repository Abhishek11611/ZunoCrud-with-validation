package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;
import com.example.demo.requestdto.PersonalDetailsListing;
import com.example.demo.requestdto.PersonalDetailsSearch;

import jakarta.servlet.http.HttpServletResponse;

public interface PersonalDetailsService {
	
	public PersonalDetailsEntity addPerson(PdRequestDto pdRequestDto);
	
//	public List<PersonalDetailsEntity> getAllPersonDetails(PersonalDetailsListing personalDetailsListing);
	
	public String deletePersonDetails(Integer personId);
	
	public PersonalDetailsEntity updatepersonById(Integer personId,PdRequestDto pdRequestDto);
	
	public PdRequestDto findbyidPersonDetails(Integer personId);
	
	public Integer countAllProposal();
	
	public List<PersonalDetailsEntity> fetchAllByStringbuilder(PersonalDetailsListing personalDetailsListing);
	
//	public void generateExcel(HttpServletResponse response) throws IOException;
	
	public String generateExcel() throws IOException;
	
	public String savedatafromexcel(MultipartFile file) throws IOException;
	
	public String excelBatchProcessing(MultipartFile file) throws IOException;
	
	public void batchProcessing() throws FileNotFoundException;
}
