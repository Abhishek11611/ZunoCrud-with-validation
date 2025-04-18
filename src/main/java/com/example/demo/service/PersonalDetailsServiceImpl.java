package com.example.demo.service;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NominieeDetailsEntity;
import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.repository.NomineeDetailsRepository;
import com.example.demo.repository.PersonalDetailsRepository;
import com.example.demo.requestdto.NomineeRequestDto;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {
	
	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;
	
	@Autowired
	private NomineeDetailsRepository nomineeDetailsRepository;
	
//	@Autowired
//	private NomineeRequestDto nomineeRequestDto;

	@Override
	public String addPerson(PdRequestDto pdRequestDto) {

	    PersonalDetailsEntity pdobj = new PersonalDetailsEntity();
	    List<String> errors = new ArrayList<>();

	    if (pdRequestDto.getPersonTilte() == null || pdRequestDto.getPersonTilte().toString().isEmpty()) {
	        errors.add("Title is missing");
	    }

	    
	    if(pdRequestDto.getPersonFirstName() == null || pdRequestDto.getPersonFirstName().isEmpty()) {
	    	errors.add("FirstName is missing");
	    }

	    if (pdRequestDto.getPersonGender() == null || pdRequestDto.getPersonGender().toString().isEmpty()) {
	        errors.add("Gender is missing");
	    }

	    if (pdRequestDto.getPersonDateOfBirth() == null || pdRequestDto.getPersonDateOfBirth().toString().isEmpty()) {
	        errors.add("Date of Birth is missing");
	    }

	    if (pdRequestDto.getPersonPanNumber() == null || pdRequestDto.getPersonPanNumber().isEmpty()) {
	        errors.add("PAN Number is missing");
	    } else if (pdRequestDto.getPersonPanNumber().length() != 10 || !pdRequestDto.getPersonPanNumber().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
	        errors.add("Please enter a valid PAN Number");
	    } else if(personalDetailsRepository.existsByPersonPanNumber(pdRequestDto.getPersonPanNumber())) {
	    	errors.add("Pancard Already Existing");
	    }

	    if (pdRequestDto.getPersonAadhaarNumber() == null || pdRequestDto.getPersonAadhaarNumber().toString().isEmpty()) {
	        errors.add("Aadhaar Number is missing");
	    } else if (pdRequestDto.getPersonAadhaarNumber().toString().length() != 12) {
	        errors.add("Please enter a 12-digit Aadhaar Number");
	    } else if (personalDetailsRepository.existsByPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber())) {
	    	errors.add("Aadhar Already Existing");
	    }

	    if (pdRequestDto.getPersonMaritalStatus() == null) {
	        errors.add("Marital Status is missing");
	    }

	    if (pdRequestDto.getPersonEmail() == null) {
	        errors.add("Email is missing");
	    }else if(personalDetailsRepository.existsByPersonEmail(pdRequestDto.getPersonEmail())) {
	    	errors.add("Email Already Existing");	    	
	    	
	    }

	    if (pdRequestDto.getPersonMobileNo() == null) {
	        errors.add("Mobile Number is missing");
	    } else if (personalDetailsRepository.existsByPersonMobileNo(pdRequestDto.getPersonMobileNo())) {
	    	errors.add("Mobile Number Already Existing");
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
//	    pdobj.setPersonFullName(pdRequestDto.getPersonFullName());
	    pdobj.setPersonFirstName(pdRequestDto.getPersonFirstName());
	    pdobj.setPersonMiddleName(pdRequestDto.getPersonMiddleName());
	    pdobj.setPersonLastName(pdRequestDto.getPersonLastName());
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
	    
	    

	  PersonalDetailsEntity personalid= personalDetailsRepository.save(pdobj);
	    
	    List<NomineeRequestDto> nomineeDetail = pdRequestDto.getNomineeDetails();
	    
	    List<NominieeDetailsEntity> list = new ArrayList<>();
	    
	    for (NomineeRequestDto nomineeRequestDto : nomineeDetail) {
	    	
			NominieeDetailsEntity nominee = new NominieeDetailsEntity();
			
			nominee.setNomineeName(nomineeRequestDto.getNomineeName());
			nominee.setNomineeNumber(nomineeRequestDto.getNomineeNumber());
			nominee.setNomineeDateOfBirth(nomineeRequestDto.getNomineeDateOfBirth());
			nominee.setNomineeGender(nomineeRequestDto.getNomineeGender());
			nominee.setNomineeRelationship(nomineeRequestDto.getNomineeRelationship());
			nominee.setPersonId(personalid.getPersonId());
			
			list.add(nominee);	    	
		}
	    
	    nomineeDetailsRepository.saveAll(list);
	    
	    return "Person And Nominee Added Successfully";
	}


	@Override
	public List<PdRequiredDto> getAllPersonDetails() {
		List<PersonalDetailsEntity> perlist= personalDetailsRepository.findByStatus("Yes");
		List <PdRequiredDto> dtobj= new ArrayList<>();
		
		
		for (PersonalDetailsEntity personalDetailsEntity : perlist) {
			
			PdRequiredDto dtoobj = new PdRequiredDto();
			
			dtoobj.setPersonTilte(personalDetailsEntity.getPersonTilte());
			dtoobj.setPersonGender(personalDetailsEntity.getPersonGender());
			dtoobj.setPersonDateOfBirth(personalDetailsEntity.getPersonDateOfBirth());
			dtoobj.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
			dtoobj.setPersonAadhaarNumber(personalDetailsEntity.getPersonAadhaarNumber());
			dtoobj.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
			dtoobj.setPersonAddress2(personalDetailsEntity.getPersonAddress2());
			dtoobj.setPersonAddress3(personalDetailsEntity.getPersonAddress3());
			dtoobj.setPersonMobileNo(personalDetailsEntity.getPersonMobileNo());
			dtoobj.setPersonAlternateMobileNo(personalDetailsEntity.getPersonAlternateMobileNo());
			dtoobj.setPersonPincode(personalDetailsEntity.getPersonPincode());
			dtoobj.setPersonCity(personalDetailsEntity.getPersonCity());
			dtoobj.setPersonState(personalDetailsEntity.getPersonState());
			dtoobj.setStatus(personalDetailsEntity.getStatus());
			dtoobj.setPersonPanNumber(personalDetailsEntity.getPersonPanNumber());
			dtoobj.setPersonMaritalStatus(personalDetailsEntity.getPersonMaritalStatus());
			dtoobj.setPersonEmail(personalDetailsEntity.getPersonEmail());
			
//			dtoobj.setPersonFullName(personalDetailsEntity.getPersonFullName());
			
			StringBuilder builder = new StringBuilder();
			
			builder.append(personalDetailsEntity.getPersonFirstName()+" ");
			
			if(personalDetailsEntity.getPersonMiddleName()!=null) {
				builder.append(personalDetailsEntity.getPersonMiddleName());
			}
			builder.append(" "+personalDetailsEntity.getPersonLastName());
			
			dtoobj.setPersonFullName(builder.toString());
            
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
//			personget.setPersonFullName(pdRequestDto.getPersonFullName());
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
	public PdRequestDto findbyidPersonDetails(Integer personId) {
		
		Optional<PersonalDetailsEntity> per = personalDetailsRepository.findById(personId);
		PdRequestDto pddto = new PdRequestDto();
		
		if (per.isPresent()) {
			
			PersonalDetailsEntity perdetails = per.get();	
			
	
			pddto.setPersonFirstName(perdetails.getPersonFirstName());
			pddto.setPersonEmail(perdetails.getPersonEmail());
			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonAadhaarNumber(perdetails.getPersonAadhaarNumber());
			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonAddress1(perdetails.getPersonAddress1());
			pddto.setPersonAlternateMobileNo(perdetails.getPersonAlternateMobileNo());
			pddto.setPersonCity(perdetails.getPersonCity());
			pddto.setPersonDateOfBirth(perdetails.getPersonDateOfBirth());
			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonLastName(perdetails.getPersonLastName());
			pddto.setPersonAddress2(perdetails.getPersonAddress2());
			pddto.setPersonLastName(perdetails.getPersonLastName());
			pddto.setPersonGender(perdetails.getPersonGender());
			pddto.setPersonPanNumber(perdetails.getPersonPanNumber());
			pddto.setPersonMaritalStatus(perdetails.getPersonMaritalStatus());
			pddto.setPersonMobileNo(perdetails.getPersonMobileNo());
			pddto.setPersonAddress3(perdetails.getPersonAddress3());
			pddto.setPersonPincode(perdetails.getPersonPincode());
			pddto.setPersonTilte(perdetails.getPersonTilte());
			pddto.setPersonState(perdetails.getPersonState());
			
			Integer proposerId = perdetails.getPersonId();
			
		List<NominieeDetailsEntity> nomieobj=nomineeDetailsRepository.getAllByPersonId(personId);
		
		List<NomineeRequestDto> list = new ArrayList<>();
			
			for (NominieeDetailsEntity nominieeDetailsEntity : nomieobj) {
				
				
				NomineeRequestDto nomidto = new NomineeRequestDto();
				
				nomidto.setNomineeName(nominieeDetailsEntity.getNomineeName());
				nomidto.setNomineeDateOfBirth(nominieeDetailsEntity.getNomineeDateOfBirth());
				nomidto.setNomineeGender(nominieeDetailsEntity.getNomineeGender());
				nomidto.setNomineeNumber(nominieeDetailsEntity.getNomineeNumber());
				nomidto.setNomineeRelationship(nominieeDetailsEntity.getNomineeRelationship());
				nomidto.setPersonId(nominieeDetailsEntity.getPersonId());
				
				list.add(nomidto);
			}
			
			pddto.setNomineeDetails(list);
		
		}
		return pddto;
	}

}
