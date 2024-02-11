package com.example.springbootpractice.dto;

public class ProductResponseDto {

	private int Id;
    private String name;
    private String description;
    private int quantity;
    public double price;
    public double modifiedPrice;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getModifiedPrice() {
		return modifiedPrice;
	}
	public void setModifiedPrice(double modifiedPrice) {
		this.modifiedPrice = modifiedPrice;
	}
    
    
}
