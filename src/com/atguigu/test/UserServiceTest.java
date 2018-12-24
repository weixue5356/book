package com.atguigu.test;

import org.junit.Test;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;

public class UserServiceTest {

	@Test
	public void testLogin() {
		System.out.println(new UserServiceImpl().login(new User(0, "admin",
				"admin", null)));
	}

	@Test
	public void testRegist() {
		new UserServiceImpl().regist(new User(0, "abc168", "123456", "abc168@qq.com"));
	}

	@Test
	public void testExistsUsername() {
		if (new UserServiceImpl().existsUsername("wzg111")) {
			System.out.println("用户名已存在！");
		} else {
			System.out.println("用户名可用！");
		}
	}

}
