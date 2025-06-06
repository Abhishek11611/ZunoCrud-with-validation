package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PersonalDetailsListing;
import com.example.demo.requestdto.PersonalDetailsSearch;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.PersonalDetailsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/personaldetails_api")
public class PersonalDetailsController {

	@Autowired
	private PersonalDetailsService personalDetailsService;

	@PostMapping("/add")
	public ResponseHandler addperson(@RequestBody PdRequestDto pdRequestDto) {

		ResponseHandler response = new ResponseHandler();

		try {
		PersonalDetailsEntity person = personalDetailsService.addPerson(pdRequestDto);

			response.setData(person);
			response.setStatus(true);
			response.setMessage("Success");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());

		} catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());

		}

		return response;
	}

	@PostMapping("/listing")
	public ResponseHandler getAllPersonDetails(@RequestBody PersonalDetailsListing personalDetailsListing) {
		ResponseHandler response = new ResponseHandler();

		Integer countAllProposal = personalDetailsService.countAllProposal();

		try {
			List<PersonalDetailsEntity> data = personalDetailsService.fetchAllByStringbuilder(personalDetailsListing);

			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			response.setTotalRecord(countAllProposal);

//			PersonalDetailsSearch search = personalDetailsListing.getPersonalDetailsSearch();
//
//			if (search != null || !search.getPersonFirstName().isEmpty() || !search.getPersonLastName().isEmpty()
//					|| !search.getPersonMobileNo().toString().isEmpty()) {
//				response.setTotalRecord(data.size());
//			} else {
//				response.setTotalRecord(countAllProposal);
//			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());

		}

		return response;
	}

	@PatchMapping("/delete_by_id/{personId}")
	public ResponseHandler deletePersonDetails(@PathVariable Integer personId) {

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

	@PutMapping("/update_by_id/{personId}")
	public ResponseHandler updatepersonById(@PathVariable Integer personId, @RequestBody PdRequestDto pdRequestDto) {
		ResponseHandler response = new ResponseHandler();

		try {
			 PersonalDetailsEntity updatepersonById = personalDetailsService.updatepersonById(personId, pdRequestDto);
			response.setData(updatepersonById);
			response.setStatus(true);
			response.setMessage("Success");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");
		}
		return response;

	}

	@GetMapping("/list_by_id/{personId}")
	public ResponseHandler findbyidPersonDetails(@PathVariable Integer personId) {
		ResponseHandler response = new ResponseHandler();

		try {
			PdRequestDto data = personalDetailsService.findbyidPersonDetails(personId);
			response.setData(data);
			response.setStatus(true);
			response.setMessage("Success");
			

		} catch (IllegalArgumentException e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");
		}
 
		catch (Exception e) {
			response.setData(new ArrayList<>());
			response.setStatus(false);
			response.setMessage("failed");
		}

		return response;

	}
//================================================ Excel Export ======================================================================
	
//	@GetMapping("/exportexcel")
//	public void generateExcelReport(HttpServletResponse response) throws Exception {
//	    response.setContentType("application/octet-stream");
//
//	    String headerKey = "Content-Disposition";
//	    String headerValue = "attachment; filename=proposal.xlsx";
//
//	    response.setHeader(headerKey, headerValue);
//
//	    personalDetailsService.generateExcel(response);
//	}
	
	@GetMapping("/exportexcel")
	public ResponseHandler generateExcelReport(HttpServletResponse response) {

		ResponseHandler responses = new ResponseHandler();

		try {
			String filePath = personalDetailsService.generateExcel();

			responses.setStatus(true);
			responses.setMessage("Excel exported successfully");
			responses.setData(filePath);

		} catch (Exception e) {
			responses.setStatus(false);
			responses.setMessage("Excel export failed: " + e.getMessage());
			responses.setData(null);
		}

		return responses;
	}

//	================================================== Import Excel ===================================================

	@PostMapping(value = "/uploadexcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public ResponseHandler uploadExcelFile(@RequestParam("file") MultipartFile file) {
		
		ResponseHandler response = new ResponseHandler();
		
		try {
			personalDetailsService.savedatafromexcel(file);
			response.setStatus(true);
			response.setMessage("Excel data saved successfully");
			response.setData(null);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed to upload Excel: " + e.getMessage());
			response.setData(null);
		}
		return response;
	}
	
//	======================================= Batch Processing =============================================================
	
	@PostMapping(value = "/uploadexcelfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public ResponseHandler excelBatchProcessing(@RequestParam("file") MultipartFile file) {
		
		ResponseHandler response = new ResponseHandler();
		
		try {
			personalDetailsService.excelBatchProcessing(file);
			response.setStatus(true);
			response.setMessage("Excel data saved successfully");
			response.setData(null);
		} catch (Exception e) {
			response.setStatus(false);
			response.setMessage("Failed to upload Excel: " + e.getMessage());
			response.setData(null);
		}
		return response;
	}

}
