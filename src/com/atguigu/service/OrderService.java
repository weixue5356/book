package com.atguigu.service;

import com.atguigu.pojo.Cart;

public interface OrderService {

	public String createOrder(Cart cart,int userId);

}
