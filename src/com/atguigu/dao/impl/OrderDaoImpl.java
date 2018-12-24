package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	@Override
	public int saveOrder(Order order) {
		
		System.out.println("OrderDaoImpl 中 线程【"
				+ Thread.currentThread().getName() + "】");
		
		String sql = "insert into t_order(`order_id`,`create_time`,`status`,`price`,`user_id`) values(?,?,?,?,?)";
		return update(sql, order.getOrderId(), order.getCreateTime(),
				order.getStatus(), order.getPrice(), order.getUserId());
	}

}
