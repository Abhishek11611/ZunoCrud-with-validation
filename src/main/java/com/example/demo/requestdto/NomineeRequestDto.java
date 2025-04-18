package com.example.demo.requestdto;

import java.sql.Date;

import com.example.demo.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class NomineeRequestDto {

    private String nomineeName;
	
	private Long nomineeNumber;
	
    private Gender nomineeGender;
	
	private String nomineeRelationship;
	
	private Date nomineeDateOfBirth;
	
	private Integer personId;

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public Long getNomineeNumber() {
		return nomineeNumber;
	}

	public void setNomineeNumber(Long nomineeNumber) {
		this.nomineeNumber = nomineeNumber;
	}

	public Gender getNomineeGender() {
		return nomineeGender;
	}

	public void setNomineeGender(Gender nomineeGender) {
		this.nomineeGender = nomineeGender;
	}

	public String getNomineeRelationship() {
		return nomineeRelationship;
	}

	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
	}

	public Date getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(Date nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	
}
