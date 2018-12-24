package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.queryUserByUsernameAndPassword(user.getUsername(),
				user.getPassword());
	}

	@Override
	public void regist(User user) {
		userDao.saveUser(user);
	}

	@Override
	public boolean existsUsername(String username) {
		User user = userDao.queryUserByUsername(username);
		// 如果查找到用户信息。说明用户名已存在
		// 返回true就是用户名已存在
		if (user != null) {
			return true;
		}
		// 用户名不存在，可用
		return false;
	}

}
