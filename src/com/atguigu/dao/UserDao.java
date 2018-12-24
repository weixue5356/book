package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserDao {
	// 保存用户
	public int saveUser(User user);

	// 根据用户名密码查询用户
	public User queryUserByUsernameAndPassword(String username, String password);

	// 根据用户名查询用户
	public User queryUserByUsername(String username);

}
