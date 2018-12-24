package com.atguigu.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;

public class UserDaoTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String xx = "中文";
		
		
		
//		System.out.println( xx.getBytes("UTF-8").length );
//		System.out.println( xx.getBytes("GBK").length );
	}
	
	@Test
	public void testSaveUser() {
		UserDao userDao = new UserDaoImpl();
		userDao.saveUser(new User(0, "wzg168", "168168", "wzg168@qq.com"));
		userDao.saveUser(new User(0, "aaaaa", "123456", "aaaa@qq.com"));
		userDao.saveUser(new User(0, "bbbbb", "123456", "bbbb@qq.com"));
		userDao.saveUser(new User(0, "ccccc", "123456", "cccc@qq.com"));
		userDao.saveUser(new User(0, "ddddd", "123456", "dddd@qq.com"));
	}

	@Test
	public void testQueryUserByUsernameAndPassword() {
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.queryUserByUsernameAndPassword("admin",
				"admin"));
		System.out.println(userDao.queryUserByUsernameAndPassword("admin",
				"123412"));
	}

	@Test
	public void testQueryUserByUsername() {
		UserDao userDao = new UserDaoImpl();
		System.out.println(userDao.queryUserByUsername("wzg168"));
		System.out.println(userDao.queryUserByUsername("asdfas"));
	}
}
