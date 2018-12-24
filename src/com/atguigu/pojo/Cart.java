package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();

	public static void main(String[] args) {
		// 精度测试
		double d1 = 0.01, d2 = 0.06, d3;
		d3 = d1 + d2;
		System.out.println(d3); // 0.07

		d1 = 0.7;
		System.out.println(d1 + d1 + d1); // 应该是2.1

		BigDecimal bigDecimal = new BigDecimal("0.7");
		bigDecimal = bigDecimal.add(bigDecimal);
		System.out.println( bigDecimal );
		
//		System.out.println(bigDecimal.add(bigDecimal).add(bigDecimal));
//
//		BigDecimal bigDecimal2 = new BigDecimal(
//				"11111111111111111111111.1111111111111111111111111111111111111111");
//		System.out.println(bigDecimal2.add(bigDecimal2));
//		
//		BigDecimal bigDecimal3 = new BigDecimal(0.1+"");
//		System.out.println( bigDecimal3 );
		
		
	}

	/**
	 * 添加商品项
	 * 
	 * @param cartItem
	 */
	public void addItem(CartItem cartItem) {
		// 先看看购物车中，有没有我们要添加的商品
		CartItem temp = items.get(cartItem.getId());
		// 如果之前购物车中没有商品，直接添加
		if (temp == null) {
			items.put(cartItem.getId(), cartItem);
		} else {
			// 如果有此添加的商品，则只需要修改原来的商品数量和总金额
			temp.setCount(temp.getCount() + 1);
			temp.setTotalPrice(temp.getPrice().multiply( new BigDecimal(temp.getCount()) ));
		}
	}

	/**
	 * 删除商品数量
	 */
	public void deleteItem(int id) {
		items.remove(id);
	}

	/**
	 * 修改商品数量
	 */
	public void updateCount(int id, int count) {
		CartItem temp = items.get(id);
		if (temp != null) {
			// 修改数量
			temp.setCount(count);
			// 修改总金额
			temp.setTotalPrice( temp.getPrice().multiply( new BigDecimal( temp.getCount() ) ) );
		}
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		items.clear();
	}

	public int getTotalCount() {
		int totalCount = 0;
		for (CartItem item : items.values()) {
			totalCount += item.getCount();
		}
		return totalCount;
	}

	public BigDecimal getToatlPrice() {
		BigDecimal totalPrice = new BigDecimal(0);
		for (CartItem item : items.values()) {
			totalPrice = totalPrice.add( item.getTotalPrice() );
		}
		return totalPrice;
	}

	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}

	public Map<Integer, CartItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Cart [totalCount=" + getTotalCount() + ", toatlPrice="
				+ getToatlPrice() + ", items=" + items + "]";
	}

}
