package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

	private String orderId;
	private Date createTime;
	private BigDecimal price;
	private int status;
	private int userId;

	public Order(String orderId, Date createTime, BigDecimal price, int status,
			int userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.price = price;
		this.status = status;
		this.userId = userId;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createTime=" + createTime
				+ ", price=" + price + ", status=" + status + ", userId="
				+ userId + "]";
	}

}
