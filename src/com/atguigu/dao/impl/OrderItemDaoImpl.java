package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements
		OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		System.out.println("OrderItemDaoImpl 中 线程【"
				+ Thread.currentThread().getName() + "】");
		String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)"
				+ " values(?,?,?,?,?)";
		return update(sql, orderItem.getName(), orderItem.getCount(),
				orderItem.getPrice(), orderItem.getTotalPrice(),
				orderItem.getOrderId());
	}

}
