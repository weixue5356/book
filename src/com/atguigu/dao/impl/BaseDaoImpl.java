package com.atguigu.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JdbcUtils;

public class BaseDaoImpl<T> {

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> type;

	public BaseDaoImpl() {
		// 获取带有泛型信息父类
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	/**
	 * 查询一条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public T queryForOne(String sql, Object... params) {
		Connection connection = JdbcUtils.getConnection();
		try {
			return queryRunner.query(connection, sql, new BeanHandler<T>(type),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询返回某个列的值的方法
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object queryForSingleValue(String sql, Object... params) {
		Connection connection = JdbcUtils.getConnection();
		try {
			return queryRunner.query(connection, sql, new ScalarHandler(),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> queryForList(String sql, Object... params) {
		Connection connection = JdbcUtils.getConnection();
		try {
			return queryRunner.query(connection, sql, new BeanListHandler<T>(
					type), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 执行insert增，delete删，update改。语句<br/>
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		System.out.println("BaseDaoImpl 中 线程【"
				+ Thread.currentThread().getName() + "】");
		Connection connection = JdbcUtils.getConnection();
		try {
			return queryRunner.update(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
