 package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.pagination.PersonalDetailsListing;
import com.example.demo.pagination.PersonalDetailsSearch;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.PersonalDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/personaldetails-api")
public class PersonalDetailsController {
	
	@Autowired
	private PersonalDetailsService personalDetailsService;
	
	@PostMapping("/add-person")
	public ResponseHandler addperson(@RequestBody PdRequestDto pdRequestDto) {

	    ResponseHandler response = new ResponseHandler();

	    try {
	        String result = personalDetailsService.addPerson(pdRequestDto);

	        response.setData(result);
            response.setStatus(true);
            response.setMessage("Success");

	    } catch (IllegalArgumentException e) {
	    	e.printStackTrace();
	        response.setData(new ArrayList<>());
	        response.setStatus(false);
	        response.setMessage(e.getMessage());
	       
	    }catch (Exception e) {
	        response.setData(new ArrayList<>());
	        response.setStatus(false);
	        response.setMessage(e.getMessage());
	       
	    }

	    return response;
	}

	   
	
	@PostMapping("/getall-person")
	public ResponseHandler getAllPersonDetails(@RequestBody PersonalDetailsListing personalDetailsListing){
		ResponseHandler response = new ResponseHandler();
		
		Integer countAllProposal = personalDetailsService.countAllProposal();
		
		try {
			List<PersonalDetailsEntity> data =	personalDetailsService.fetchAllByStringbuilder(personalDetailsListing);
			
			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			
			PersonalDetailsSearch search = personalDetailsListing.getPersonalDetailsSearch();
			
			if(search!=null || !search.getPersonFirstName().isEmpty() || !search.getPersonLastName().isEmpty() || !search.getPersonMobileNo().toString().isEmpty()) {
				response.setTotalRecord(data.size());
			}
			else {
				response.setTotalRecord(countAllProposal);
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());
			
		}
		
	
		return response;
	}
	
	@PatchMapping("/delete-person/{personId}")
	public ResponseHandler deletePersonDetails(@PathVariable Integer personId){
		
		ResponseHandler response = new ResponseHandler();
		
		try {
			String data = personalDetailsService.deletePersonDetails(personId);
			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			
		} catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");		
		}	
		
		
		
		return response;
	}
   
	@PutMapping("/update-person/{personId}")
	public ResponseHandler updatepersonById(@PathVariable Integer personId,@RequestBody PdRequestDto pdRequestDto){
		ResponseHandler response = new ResponseHandler();
		
		try {
			String data = personalDetailsService.updatepersonById(personId, pdRequestDto);
			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());		
		}
		catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");		
		}	
		return response;
		
	}
	
	@GetMapping("/get-personbyid/{personId}")
	public ResponseHandler findbyidPersonDetails(@PathVariable Integer personId) {
		ResponseHandler response = new ResponseHandler();
		
		try {
			PdRequestDto data = personalDetailsService.findbyidPersonDetails(personId);
			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			
		} catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");		
		}	
		
		return response;
		
	}
	
	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		
		String headerKey ="Content-Disposition";
		String headerValue = "attachment;filename=proposal.xls";
		
		response.setHeader(headerKey, headerValue);
		
		personalDetailsService.generateExcel(response);
	}
	
}
