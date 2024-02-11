package com.example.springbootpractice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscountTaxDto {
	private double rate;
	
	@JsonProperty("isDiscount")
	private boolean isDiscount;
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public boolean isDiscount() {
		return isDiscount;
	}
	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
	

}
