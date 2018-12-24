package com.atguigu.pojo;

import java.math.BigDecimal;

public class OrderItem {

	private int id;
	private String name;
	private int count;
	private BigDecimal price;
	private BigDecimal totalPrice;
	private String orderId;

	public OrderItem(int id, String name, int count, BigDecimal price,
			BigDecimal totalPrice, String orderId) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderItem() {
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
		return "OrderItem [id=" + id + ", name=" + name + ", count=" + count
				+ ", price=" + price + ", totalPrice=" + totalPrice
				+ ", orderId=" + orderId + "]";
	}

}
