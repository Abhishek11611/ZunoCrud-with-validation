package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "que_table")
public class QueTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer queId;
	private String filePath;
	private String isProcess;
	private Integer rowCount;
	private Integer rowRead;
	private String status;
	private Integer lastProcessCount;
	
	public QueTable() {
		// TODO Auto-generated constructor stub
	}

	public QueTable(Integer queId, String filePath, String isProcess, Integer rowCount, Integer rowRead, String status,
			Integer lastProcessCount) {
		super();
		this.queId = queId;
		this.filePath = filePath;
		this.isProcess = isProcess;
		this.rowCount = rowCount;
		this.rowRead = rowRead;
		this.status = status;
		this.lastProcessCount = lastProcessCount;
	}

	public Integer getQueId() {
		return queId;
	}

	public void setQueId(Integer queId) {
		this.queId = queId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(String isProcess) {
		this.isProcess = isProcess;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getRowRead() {
		return rowRead;
	}

	public void setRowRead(Integer rowRead) {
		this.rowRead = rowRead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLastProcessCount() {
		return lastProcessCount;
	}

	public void setLastProcessCount(Integer lastProcessCount) {
		this.lastProcessCount = lastProcessCount;
	}
	
	
}
