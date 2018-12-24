package com.atguigu.pojo;

import java.math.BigDecimal;

public class CartItem {

	private int id;
	private String name;
	private int count;
	private BigDecimal price;
	private BigDecimal totalPrice;

	public CartItem(int id, String name, int count, double price,
			double totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = new BigDecimal(price + "");
		this.totalPrice = new BigDecimal(totalPrice + "");
	}

	public CartItem(int id, String name, int count, BigDecimal price,
			BigDecimal totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", count=" + count
				+ ", price=" + price + ", totalPrice=" + totalPrice + "]";
	}

}
