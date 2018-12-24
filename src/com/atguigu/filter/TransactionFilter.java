package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.atguigu.utils.JdbcUtils;

public class TransactionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			// 调用程序实际访问的目标资源代码
			chain.doFilter(request, response);
			// 提交事务并关闭连接
			JdbcUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚 事务，并关闭连接
			JdbcUtils.rollbackAndClose();
			// 把异常交给服务器。
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
