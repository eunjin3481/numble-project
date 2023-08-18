package com.numble.domain;

public class StockPrice {
	private int id;
	private int stock_id;
	private int price;
	private String created_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
