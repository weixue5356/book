package com.atguigu.service.impl;

import java.util.Date;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao OrderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public String createOrder(Cart cart, int userId) {

		System.out.println("OrderServiceImpl 中 线程【"
				+ Thread.currentThread().getName() + "】");

		String orderId = System.currentTimeMillis() + "" + userId;
		Order order = new Order(orderId, new Date(), cart.getToatlPrice(), 0,
				userId);
		// 保存订单
		orderDao.saveOrder(order);
		//
		int i = 12 / 0;

		// 还需要保存订单项。------>>>> 订单项，其实就是购物车中的商品项
		for (CartItem cartItem : cart.getItems().values()) {
			// 遍历每一个商品项，生成订单项，然后保存
			OrderItem orderItem = new OrderItem(0, cartItem.getName(),
					cartItem.getCount(), cartItem.getPrice(),
					cartItem.getTotalPrice(), orderId);
			// 保存了订单项
			OrderItemDao.saveOrderItem(orderItem);
			// 查询出购物车中的商品信息
			Book book = bookDao.queryBookById(cartItem.getId());
			// 修改销量
			book.setSales(book.getSales() + cartItem.getCount());
			book.setStock(book.getStock() - cartItem.getCount());
			bookDao.updateBook(book);
		}
		// 清空了购物车
		cart.clear();

		return orderId;
	}

}
