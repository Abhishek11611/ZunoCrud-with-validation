package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.repository.PersonalDetailsRepository;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {
	
	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;

	@Override
	public PersonalDetailsEntity addPerson(PersonalDetailsEntity personalDetailsEntity) {
	PersonalDetailsEntity per	=personalDetailsRepository.save(personalDetailsEntity);
		return per;
	}

	@Override
	public List<PersonalDetailsEntity> getAllPersonDetails() {
		List<PersonalDetailsEntity> perlist= personalDetailsRepository.findByStatus("Yes");
		return perlist;
		
//		return  personalDetailsRepository.findAll();
	
	}

	@Override
	public String deletePersonDetails(Integer personId) {
//		personalDetailsRepository.deleteById(personId);
		
		Optional<PersonalDetailsEntity> per= personalDetailsRepository.findById(personId);
		
		if(per.isPresent()) {
			PersonalDetailsEntity personget=per.get();
			
			personget.setStatus("No");
			personalDetailsRepository.save(personget);
			
			
		}
		return "Person Delete Succesfully!!";

	}

	@Override
	public String updatepersonById(Integer personId, PersonalDetailsEntity personalDetailsEntity) {
		Optional<PersonalDetailsEntity> per= personalDetailsRepository.findById(personId);
		
		if(per.isPresent()) {
			
			PersonalDetailsEntity personget=per.get();
			
			personget.setPersonTilte(personalDetailsEntity.getPersonTilte());
			personget.setPersonFullName(personalDetailsEntity.getPersonFullName());
			personget.setPersonGender(personalDetailsEntity.getPersonGender());
			personget.setPersonDateOfBirth(personalDetailsEntity.getPersonDateOfBirth());
			personget.setPersonPanNumber(personalDetailsEntity.getPersonPanNumber());
			personget.setPersonAadhaarNumber(personalDetailsEntity.getPersonAadhaarNumber());
			personget.setPersonMaritalStatus(personalDetailsEntity.getPersonMaritalStatus());
			personget.setPersonEmail(personalDetailsEntity.getPersonEmail());
			personget.setPersonMobileNo(personalDetailsEntity.getPersonMobileNo());
			personget.setPersonAlternateMobileNo(personalDetailsEntity.getPersonAlternateMobileNo());
			personget.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
			personget.setPersonAddress2(personalDetailsEntity.getPersonAddress2());
			personget.setPersonAddress3(personalDetailsEntity.getPersonAddress3());
			personget.setPersonPincode(personalDetailsEntity.getPersonPincode());
			personget.setPersonCity(personalDetailsEntity.getPersonCity());
			personget.setPersonState(personalDetailsEntity.getPersonState());
			
			personalDetailsRepository.save(personget);
			
			return "Person Details Update Succesfully!!";		}
		else {
			return "Person Details not updated";
		}
		
	}

}
