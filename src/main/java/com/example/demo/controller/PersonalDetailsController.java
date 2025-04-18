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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.PersonalDetailsService;

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

	    } catch (Exception e) {
	        response.setData(new ArrayList<>());
	        response.setStatus(false);
	        response.setMessage(e.getMessage());
	       
	    }

	    return response;
	}

	   
	
	@GetMapping("/getall-person")
	public ResponseHandler getAllPersonDetails(){
		ResponseHandler response = new ResponseHandler();
		
		try {
			List<PdRequiredDto> data =	personalDetailsService.getAllPersonDetails();
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
			
		} catch (Exception e) {
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
}
