package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
	// 登录业务
	public User login(User user);

	// 注册业务
	public void regist(User user);

	// 判断用户名是否存在
	public boolean existsUsername(String username);
}
