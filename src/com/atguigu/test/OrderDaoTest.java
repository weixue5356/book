package com.atguigu.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;

public class OrderDaoTest {

	@Test
	public void testSaveOrder() {
		new OrderDaoImpl().saveOrder(new Order("1234123412", new Date(), new BigDecimal(100), 0, 1));
	}

}
