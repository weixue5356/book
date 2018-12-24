package com.atguigu.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;

public class OrderItemDaoTest {

	@Test
	public void testSaveOrderItem() {
		new OrderItemDaoImpl().saveOrderItem(new OrderItem(0, "产后护理", 2,
				new BigDecimal(100), new BigDecimal(200), "1234123412"));
	}

}
