package com.example.demo.response;

public class ResponseHandler {

	private Object Data;
	private boolean status;
	private String message;
	private Integer totalRecord;
	
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	
	
}
