package com.makocabey.rest;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Parity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDate date;
	
	private String parityCode;
	
	private Double purchasePrice;
	
	private Double salePrice;
	
	private Double averagePrice;
	
	
	
	protected Parity() {}
	
	public Parity(String parityCode, LocalDate date, Double purchasePrice, Double salePrice, Double averagePrice) {
		this.date = date; 
		this.parityCode = parityCode;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.averagePrice = averagePrice;
	}
	
	
	
	public LocalDate getDate() {
		return date;
	}
	
	public String getParityCode() {
		return parityCode;
	}
	
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	
	public Double getSalePrice() {
		return salePrice;
	}
	
	public Double getAveragePrice() {
		return averagePrice;
	}
	
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setParityCode(String parityCode) {
		this.parityCode = parityCode;
	}
	
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Parity)) {
			return false;
		}
		
		Parity parity = (Parity) o;
		
		return Objects.equals(this.parityCode, parity.parityCode) && 
				Objects.equals(this.date, parity.date) &&
				Objects.equals(this.purchasePrice, parity.purchasePrice) &&
				Objects.equals(this.salePrice, parity.salePrice) &&
				Objects.equals(this.averagePrice, parity.averagePrice);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.parityCode);
	}
	
	
	@Override 
	public String toString() {
		return String.format("Parity[id=%d, date='%s', parityCode='%s', purchasePrice='%s',"
				+ " salePrice='%s', averagePrice='%s']", 
				id, String.valueOf(date), String.valueOf(parityCode), 
				String.valueOf(purchasePrice), String.valueOf(salePrice), 
				String.valueOf(averagePrice));
	}
}
