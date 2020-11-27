package com.makocabey.rest;


import java.time.LocalDate;



public class CustomParityResponse {

	private LocalDate date;
	
	private String parityCode;
	
	private String yieldChangeMethod;
	
	private Double yieldChange;
	
	
	
	public CustomParityResponse() {}
	
	public CustomParityResponse(LocalDate date, String parityCode, Double yieldChange, String yieldChangeMethod) {
		this.date = date;
		this.parityCode = parityCode;
		this.yieldChange = yieldChange;
		this.yieldChangeMethod = yieldChangeMethod;
	}
	
	
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getParityCode() { 
		return parityCode;
	}
	
	public Double getYieldChange() {
		return yieldChange;
	}
	
	public String getYieldChangeMethod() {
		return yieldChangeMethod;
	}
	
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setParityCode(String parityCode) { 
		this.parityCode = parityCode;
	}
	
	
	public void setYieldChange(Double yieldChange) {
		this.yieldChange = yieldChange;
	}
	
	public void setYieldChangeMethod(String yieldChangeMethod) {
		this.yieldChangeMethod = yieldChangeMethod;
	}
}
