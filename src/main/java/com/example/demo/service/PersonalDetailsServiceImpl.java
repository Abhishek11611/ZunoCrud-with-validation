package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.repository.PersonalDetailsRepository;
import com.example.demo.requestdto.PdRequestDto;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {
	
	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;

	@Override
	public String addPerson(PdRequestDto pdRequestDto) {

	    PersonalDetailsEntity pdobj = new PersonalDetailsEntity();
	    List<String> errors = new ArrayList<>();

	    if (pdRequestDto.getPersonTilte() == null || pdRequestDto.getPersonTilte().toString().isEmpty()) {
	        errors.add("Title is missing");
	    }

	    if (pdRequestDto.getPersonFullName() == null || pdRequestDto.getPersonFullName().isEmpty()) {
	        errors.add("Full Name is missing");
	    }

	    if (pdRequestDto.getPersonGender() == null || pdRequestDto.getPersonGender().toString().isEmpty()) {
	        errors.add("Gender is missing");
	    }

	    if (pdRequestDto.getPersonDateOfBirth() == null || pdRequestDto.getPersonDateOfBirth().toString().isEmpty()) {
	        errors.add("Date of Birth is missing");
	    }

	    if (pdRequestDto.getPersonPanNumber() == null || pdRequestDto.getPersonPanNumber().isEmpty()) {
	        errors.add("PAN Number is missing");
	    } else if (pdRequestDto.getPersonPanNumber().length() != 10 || 
	               !pdRequestDto.getPersonPanNumber().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
	        errors.add("Please enter a valid PAN Number");
	    }

	    if (pdRequestDto.getPersonAadhaarNumber() == null || pdRequestDto.getPersonAadhaarNumber().toString().isEmpty()) {
	        errors.add("Aadhaar Number is missing");
	    } else if (pdRequestDto.getPersonAadhaarNumber().toString().length() != 12) {
	        errors.add("Please enter a 12-digit Aadhaar Number");
	    }

	    if (pdRequestDto.getPersonMaritalStatus() == null) {
	        errors.add("Marital Status is missing");
	    }

	    if (pdRequestDto.getPersonEmail() == null) {
	        errors.add("Email is missing");
	    }

	    if (pdRequestDto.getPersonMobileNo() == null) {
	        errors.add("Mobile Number is missing");
	    }

	    if (pdRequestDto.getPersonAlternateMobileNo() == null) {
	        errors.add("Alternative Mobile Number is missing");
	    }

	    if (pdRequestDto.getPersonAddress1() == null) {
	        errors.add("Address1 is missing");
	    }

	    if (pdRequestDto.getPersonAddress2() == null) {
	        errors.add("Address2 is missing");
	    }

	    if (pdRequestDto.getPersonAddress3() == null) {
	        errors.add("Address3 is missing");
	    }

	    if (pdRequestDto.getPersonPincode() == null) {
	        errors.add("Pincode is missing");
	    }

	    if (pdRequestDto.getPersonCity() == null) {
	        errors.add("City is missing");
	    }

	    if (pdRequestDto.getPersonState() == null) {
	        errors.add("State is missing");
	    }

	    
	    if (!errors.isEmpty()) {
	        throw new IllegalArgumentException(String.join(", ", errors));
	    }

	    pdobj.setPersonTilte(pdRequestDto.getPersonTilte());
	    pdobj.setPersonFullName(pdRequestDto.getPersonFullName());
	    pdobj.setPersonGender(pdRequestDto.getPersonGender());
	    pdobj.setPersonDateOfBirth(pdRequestDto.getPersonDateOfBirth());
	    pdobj.setPersonPanNumber(pdRequestDto.getPersonPanNumber());
	    pdobj.setPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber());
	    pdobj.setPersonMaritalStatus(pdRequestDto.getPersonMaritalStatus());
	    pdobj.setPersonEmail(pdRequestDto.getPersonEmail());
	    pdobj.setPersonMobileNo(pdRequestDto.getPersonMobileNo());
	    pdobj.setPersonAlternateMobileNo(pdRequestDto.getPersonAlternateMobileNo());
	    pdobj.setPersonAddress1(pdRequestDto.getPersonAddress1());
	    pdobj.setPersonAddress2(pdRequestDto.getPersonAddress2());
	    pdobj.setPersonAddress3(pdRequestDto.getPersonAddress3());
	    pdobj.setPersonPincode(pdRequestDto.getPersonPincode());
	    pdobj.setPersonCity(pdRequestDto.getPersonCity());
	    pdobj.setPersonState(pdRequestDto.getPersonState());

	    personalDetailsRepository.save(pdobj);
	    return "Added Successfully";
	}


	@Override
	public List<PdRequestDto> getAllPersonDetails() {
		List<PersonalDetailsEntity> perlist= personalDetailsRepository.findByStatus("Yes");
		List <PdRequestDto> dtobj= new ArrayList<>();
		
		
		for (PersonalDetailsEntity personalDetailsEntity : perlist) {
			
			PdRequestDto dtoobj = new PdRequestDto();
			
			dtoobj.setPersonTilte(personalDetailsEntity.getPersonTilte());
			dtoobj.setPersonFullName(personalDetailsEntity.getPersonFullName());
			dtoobj.setPersonGender(personalDetailsEntity.getPersonGender());
			dtoobj.setPersonDateOfBirth(personalDetailsEntity.getPersonDateOfBirth());
			dtoobj.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
			dtoobj.setPersonAadhaarNumber(personalDetailsEntity.getPersonAadhaarNumber());
            
			dtobj.add(dtoobj);
		}
		
		return dtobj;
	
	}

	@Override
	public String deletePersonDetails(Integer personId) {

		
		Optional<PersonalDetailsEntity> per= personalDetailsRepository.findById(personId);
		
		if(per.isPresent()) {
			PersonalDetailsEntity personget=per.get();
			
			personget.setStatus("No");
			personalDetailsRepository.save(personget);
			
			
		}
		return "Person Delete Succesfully!!";

	}

	@Override
	public String updatepersonById(Integer personId, PdRequestDto pdRequestDto) {
		
		Optional<PersonalDetailsEntity> per =personalDetailsRepository.findByPersonIdAndStatus(personId,"Yes");
		
		if(per.isPresent()) {
			
			PersonalDetailsEntity personget=per.get();
			
			personget.setPersonTilte(pdRequestDto.getPersonTilte());
			personget.setPersonFullName(pdRequestDto.getPersonFullName());
			personget.setPersonGender(pdRequestDto.getPersonGender());
			personget.setPersonDateOfBirth(pdRequestDto.getPersonDateOfBirth());
			personget.setPersonPanNumber(pdRequestDto.getPersonPanNumber());
			personget.setPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber());
			personget.setPersonMaritalStatus(pdRequestDto.getPersonMaritalStatus());
			personget.setPersonEmail(pdRequestDto.getPersonEmail());
			personget.setPersonMobileNo(pdRequestDto.getPersonMobileNo());
			personget.setPersonAlternateMobileNo(pdRequestDto.getPersonAlternateMobileNo());
			personget.setPersonAddress1(pdRequestDto.getPersonAddress1());
			personget.setPersonAddress2(pdRequestDto.getPersonAddress2());
			personget.setPersonAddress3(pdRequestDto.getPersonAddress3());
			personget.setPersonPincode(pdRequestDto.getPersonPincode());
			personget.setPersonCity(pdRequestDto.getPersonCity()); 
			personget.setPersonState(pdRequestDto.getPersonState());
			
			personalDetailsRepository.save(personget);
			
			return "Person Details Update Succesfully!!";		}
		else {
			return "Person Details not updated";
		}
		
	}

	
	@Override
	public String findbyidPersonDetails(Integer personId) {
		Optional<PersonalDetailsEntity> per = personalDetailsRepository.findById(personId);
		
		if (per.isPresent()) {
			
			
		}
		return null;
	}

}
