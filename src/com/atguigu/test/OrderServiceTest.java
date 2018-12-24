package com.atguigu.test;

import org.junit.Test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.impl.OrderServiceImpl;

public class OrderServiceTest {

	@Test
	public void test() {
		Cart cart = new Cart();

		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(2, "产后护理II", 1, 99, 99));

		new OrderServiceImpl().createOrder(cart, 1);
	}

}
