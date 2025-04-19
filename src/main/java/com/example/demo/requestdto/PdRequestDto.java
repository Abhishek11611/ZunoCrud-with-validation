package com.example.demo.requestdto;

import java.util.Date;
import java.util.List;

import com.example.demo.entity.NominieeDetailsEntity;
import com.example.demo.enums.Gender;
import com.example.demo.enums.MaritalStatus;
import com.example.demo.enums.Title;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PdRequestDto {

	private Title PersonTilte;
	private String personFirstName;
	private String personMiddleName;
	private String personLastName;
	private Gender personGender;
	private Date personDateOfBirth;
	private String personPanNumber;
	private Long personAadhaarNumber;
	private MaritalStatus personMaritalStatus;

//Contact Details
	private String personEmail;
	private Long personMobileNo;
	private Long personAlternateMobileNo;

//Address
	private String personAddress1;
	private String personAddress2;
	private String personAddress3;
	private Long personPincode;
	private String personCity;
	private String personState;

// Status
	private String status = "Yes";
	private String isUpdate = "Y";

	

	private NomineeRequestDto nomineeDetails;

	
	
	
	public NomineeRequestDto getNomineeDetails() {
		return nomineeDetails;
	}

	public void setNomineeDetails(NomineeRequestDto nomineeDetails) {
		this.nomineeDetails = nomineeDetails;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Title getPersonTilte() {
		return PersonTilte;
	}

	public void setPersonTilte(Title personTilte) {
		PersonTilte = personTilte;
	}

	public Gender getPersonGender() {
		return personGender;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonMiddleName() {
		return personMiddleName;
	}

	public void setPersonMiddleName(String personMiddleName) {
		this.personMiddleName = personMiddleName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	public void setPersonGender(Gender personGender) {
		this.personGender = personGender;
	}

	public Date getPersonDateOfBirth() {
		return personDateOfBirth;
	}

	public void setPersonDateOfBirth(Date personDateOfBirth) {
		this.personDateOfBirth = personDateOfBirth;
	}

	public String getPersonPanNumber() {
		return personPanNumber;
	}

	public void setPersonPanNumber(String personPanNumber) {
		this.personPanNumber = personPanNumber;
	}

	public Long getPersonAadhaarNumber() {
		return personAadhaarNumber;
	}

	public void setPersonAadhaarNumber(Long personAadhaarNumber) {
		this.personAadhaarNumber = personAadhaarNumber;
	}

	public MaritalStatus getPersonMaritalStatus() {
		return personMaritalStatus;
	}

	public void setPersonMaritalStatus(MaritalStatus personMaritalStatus) {
		this.personMaritalStatus = personMaritalStatus;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public Long getPersonMobileNo() {
		return personMobileNo;
	}

	public void setPersonMobileNo(Long personMobileNo) {
		this.personMobileNo = personMobileNo;
	}

	public Long getPersonAlternateMobileNo() {
		return personAlternateMobileNo;
	}

	public void setPersonAlternateMobileNo(Long personAlternateMobileNo) {
		this.personAlternateMobileNo = personAlternateMobileNo;
	}

	public String getPersonAddress1() {
		return personAddress1;
	}

	public void setPersonAddress1(String personAddress1) {
		this.personAddress1 = personAddress1;
	}

	public String getPersonAddress2() {
		return personAddress2;
	}

	public void setPersonAddress2(String personAddress2) {
		this.personAddress2 = personAddress2;
	}

	public String getPersonAddress3() {
		return personAddress3;
	}

	public void setPersonAddress3(String personAddress3) {
		this.personAddress3 = personAddress3;
	}

	public Long getPersonPincode() {
		return personPincode;
	}

	public void setPersonPincode(Long personPincode) {
		this.personPincode = personPincode;
	}

	public String getPersonCity() {
		return personCity;
	}

	public void setPersonCity(String personCity) {
		this.personCity = personCity;
	}

	public String getPersonState() {
		return personState;
	}

	public void setPersonState(String personState) {
		this.personState = personState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
