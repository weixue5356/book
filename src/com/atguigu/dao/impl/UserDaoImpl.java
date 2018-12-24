package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public int saveUser(User user) {
		// 插入的sql语句
		String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?);";
		// 执行插入操作
		return update(sql, user.getUsername(), user.getPassword(),
				user.getEmail());
	}

	@Override
	public User queryUserByUsernameAndPassword(String username, String password) {
		// 查询的sql语句
		String sql = "select `id`,`username`,`password`,`email` from t_user where username= ? and password = ?";
		// 执行查询操作
		return queryForOne(sql, username, password);
	}

	@Override
	public User queryUserByUsername(String username) {
		// 查询的sql语句
		String sql = "select `id`,`username`,`password`,`email` from t_user where username= ?";
		// 执行查询操作
		return queryForOne(sql, username);
	}

}
