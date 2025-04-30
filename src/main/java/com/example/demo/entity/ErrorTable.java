package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "error_table_detail")
public class ErrorTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer errorId;
	private String status;
	private String error;
	private String errorField;
	
	public ErrorTable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ErrorTable [errorId=" + errorId + ", status=" + status + ", error=" + error + ", errorField="
				+ errorField + "]";
	}

	public Integer getErrorId() {
		return errorId;
	}

	public void setErrorId(Integer errorId) {
		this.errorId = errorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

   
}
