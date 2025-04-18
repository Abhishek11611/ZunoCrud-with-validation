package com.example.demo.entity;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.enums.Gender;
import com.example.demo.enums.MaritalStatus;
import com.example.demo.enums.Title;

import jakarta.persistence.Column;
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
	@Column(name = "person_id")
	private Integer personId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "person_title")
	private Title PersonTilte;
	
	
	@Column(name = "person_firstname")
	private String personFirstName;
	
	@Column(name = "person_middlename")
	private String personMiddleName;
	
	@Column(name = "person_lastname")
	private String personLastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "person_gender")
	private Gender personGender;
	
	@Column(name = "person_dateofbirth")
	private Date personDateOfBirth;
	
    @Column(name = "person_pan_number")
	private String personPanNumber;
	
    @Column(name = "person_aadhar_number")
	private Long personAadhaarNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "person_maritalstatus")
	private MaritalStatus personMaritalStatus;

//Contact Details
	@Column(name = "person_email")
	private String personEmail;
	
	@Column(name = "person_mobile_no")
	private Long personMobileNo;
	
	@Column(name = "person_alternate_mobile_no")
	private Long personAlternateMobileNo;

//Address
	@Column(name = "person_address1")
	private String personAddress1;
	
	@Column(name = "person_address2")
	private String personAddress2;
	
	@Column(name = "person_address3")
	private String personAddress3;
	
	@Column(name = "person_pincode")
	private Long personPincode;
	
	@Column(name = "person_city")
	private String personCity;
	
	@Column(name = "person_date")
	private String personState;

// Status
	@Column(name = "status")
	private String status = "Yes";

//Date
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDate createdAt;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDate updatedAt;
	


	public PersonalDetailsEntity() {
		// TODO Auto-generated constructor stub
	}

	public PersonalDetailsEntity(Integer personId, Title personTilte, String personFirstName, String personMiddleName,
			String personLastName, Gender personGender, Date personDateOfBirth, String personPanNumber,
			Long personAadhaarNumber, MaritalStatus personMaritalStatus, String personEmail, Long personMobileNo,
			Long personAlternateMobileNo, String personAddress1, String personAddress2, String personAddress3,
			Long personPincode, String personCity, String personState, String status, LocalDate createdAt,
			LocalDate updatedAt) {
		super();
		this.personId = personId;
		PersonTilte = personTilte;
		this.personFirstName = personFirstName;
		this.personMiddleName = personMiddleName;
		this.personLastName = personLastName;
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
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	

	

}