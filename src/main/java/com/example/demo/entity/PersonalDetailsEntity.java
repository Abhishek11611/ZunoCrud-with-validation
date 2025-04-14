package com.example.demo.entity;

import java.util.Date;

import com.example.demo.enums.Gender;
import com.example.demo.enums.MaritalStatus;
import com.example.demo.enums.Title;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal-details-table")
public class PersonalDetailsEntity {
	
// Personal Details
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;
	@Enumerated(EnumType.STRING )
	private Title PersonTilte;
	private String personFullName;
	@Enumerated(EnumType.STRING)
	private Gender personGender;
	private Date personDateOfBirth;
	private String personPanNumber;
	private Long personAadhaarNumber;
	@Enumerated(EnumType.STRING)
	private MaritalStatus personMaritalStatus; 
	
//Contact Details
	private String personEmail;
	private Long personMobileNo;
	private Long personAlternateMobileNo;
	
//Address
	private String personAddress1;
	private String personAddress2;
	private String personAddress3;
	private Long  personPincode;
	private String personCity;
	private String personState;
	
// Status
	private String status ="Yes";
	
	public PersonalDetailsEntity() {
		// TODO Auto-generated constructor stub
	}

	public PersonalDetailsEntity(Integer personId, Title personTilte, String personFullName, Gender personGender,
			Date personDateOfBirth, String personPanNumber, Long personAadhaarNumber, MaritalStatus personMaritalStatus,
			String personEmail, Long personMobileNo, Long personAlternateMobileNo, String personAddress1,
			String personAddress2, String personAddress3, Long personPincode, String personCity, String personState,
			String status) {
		super();
		this.personId = personId;
		PersonTilte = personTilte;
		this.personFullName = personFullName;
		this.personGender = personGender;
		this.personDateOfBirth = personDateOfBirth;
		this.personPanNumber = personPanNumber;
		this.personAadhaarNumber = personAadhaarNumber;
		this.personMaritalStatus = personMaritalStatus;
		this.personEmail = personEmail;
		this.personMobileNo = personMobileNo;
		this.personAlternateMobileNo = personAlternateMobileNo;
		this.personAddress1 = personAddress1;
		this.personAddress2 = personAddress2;
		this.personAddress3 = personAddress3;
		this.personPincode = personPincode;
		this.personCity = personCity;
		this.personState = personState;
		this.status = status;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Title getPersonTilte() {
		return PersonTilte;
	}

	public void setPersonTilte(Title personTilte) {
		PersonTilte = personTilte;
	}

	public String getPersonFullName() {
		return personFullName;
	}

	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}

	public Gender getPersonGender() {
		return personGender;
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
