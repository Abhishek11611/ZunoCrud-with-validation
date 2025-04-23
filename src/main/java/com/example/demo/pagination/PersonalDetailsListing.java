package com.example.demo.pagination;

import java.util.List;

public class PersonalDetailsListing {
	
	private Integer pageNumber;
	private Integer pageSize;
	private String sortBy;
	private String sortOrder;
	
	private PersonalDetailsSearch personalDetailsSearch;
	
	
	
	public PersonalDetailsSearch getPersonalDetailsSearch() {
		return personalDetailsSearch;
	}
	public void setPersonalDetailsSearch(PersonalDetailsSearch personalDetailsSearch) {
		this.personalDetailsSearch = personalDetailsSearch;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	

}
