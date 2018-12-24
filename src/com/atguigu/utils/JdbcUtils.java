package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	// 创建c3p0数据库连接池对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource(
			"book_devoloper");

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		// 所有的连接都必须从ThreadLocal中获取。----可以保存使用同一个Connection对象
		Connection connection = threadLocal.get();
		if (connection == null) { // 说明是第一次使用Connection
			try {
				// 第一次获取连接需要从数据库连接池中获取
				connection = dataSource.getConnection();
				// 设置为手动提交事务。-- 才能管理事务
				connection.setAutoCommit(false);
				// 存放到ThreadLocal中，方便后面再使用的时候获取。
				threadLocal.set(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * 提交事务之后马上关闭
	 */
	public static void commitAndClose() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			// 提交事务
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 一定要加上这一行代码，否则天理不容（要出错）
		threadLocal.remove();
	}

	/**
	 * 回滚事务之后马上关闭
	 */
	public static void rollbackAndClose() {
		Connection conn = threadLocal.get();
		if (conn != null) {
			// 回滚事务
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 一定要加上这一行代码，否则天理不容（要出错）
		threadLocal.remove();
	}

	/**
	 * 释放连接
	 * 
	 * @param conn
	 */
	// public static void closeConnection(Connection conn) {
	// if (conn != null) {
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }
}
