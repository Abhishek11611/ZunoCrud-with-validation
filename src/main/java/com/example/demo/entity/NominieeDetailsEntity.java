package com.example.demo.entity;

import java.sql.Date;

import com.example.demo.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nominiee_details_entity")
public class NominieeDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nominee_id")
	private Integer nomineeId;
	
	@Column(name = "nominee_name")
	private String nomineeName;
	
	@Column(name = "nominee_mobileno")
	private Long nomineeNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "nominee_gender")
	private Gender nomineeGender;
	
	@Column(name = "nominee_relationship")
	private String nomineeRelationship;
	
	@Column(name = "nominee_date_of_birth")
	private Date nomineeDateOfBirth;
	
	@Column(name = "person_id")
	private Integer personId;
	
	@Column(name = "nominee_status")
	private String nomineeStatus = "Yes";
	
	
	public NominieeDetailsEntity() {
		// TODO Auto-generated constructor stub
	}

	public NominieeDetailsEntity(Integer nomineeId, String nomineeName, Long nomineeNumber, Gender nomineeGender,
			String nomineeRelationship, Date nomineeDateOfBirth, Integer personId) {
		super();
		this.nomineeId = nomineeId;
		this.nomineeName = nomineeName;
		this.nomineeNumber = nomineeNumber;
		this.nomineeGender = nomineeGender;
		this.nomineeRelationship = nomineeRelationship;
		this.nomineeDateOfBirth = nomineeDateOfBirth;
		this.personId = personId;
	}
	
	

	public String getNomineeStatus() {
		return nomineeStatus;
	}

	public void setNomineeStatus(String nomineeStatus) {
		this.nomineeStatus = nomineeStatus;
	}

	public Integer getNomineeId() {
		return nomineeId;
	}

	public void setNomineeId(Integer nomineeId) {
		this.nomineeId = nomineeId;
	}

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
