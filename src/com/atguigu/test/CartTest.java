package com.atguigu.test;

import org.junit.Test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;

public class CartTest {

	@Test
	public void testAddItem() {
		Cart cart = new Cart();
		
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(2, "产后护理II", 1, 99, 99));
		
		System.out.println(cart);
	}

	@Test
	public void testDeleteItem() {
		Cart cart = new Cart();
		
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(2, "产后护理II", 1, 99, 99));
		
		cart.deleteItem(2);
		
		System.out.println(cart);
	}

	@Test
	public void testUpdateCount() {
		Cart cart = new Cart();
		
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(2, "产后护理II", 1, 99, 99));
		
		cart.updateCount(2, 10);
		
		
		System.out.println(cart);
	}

	@Test
	public void testClear() {
		Cart cart = new Cart();
		
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(1, "产后护理", 1, 9, 9));
		cart.addItem(new CartItem(2, "产后护理II", 1, 99, 99));
		
		cart.updateCount(2, 10);
		
		cart.clear();
		
		System.out.println(cart);
	}

}
