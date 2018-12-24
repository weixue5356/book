package com.atguigu.test;

import org.junit.Test;

import com.atguigu.utils.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void test() {
		System.out.println(JdbcUtils.getConnection());
	}

}
