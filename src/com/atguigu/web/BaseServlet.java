package com.atguigu.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		// 解决post请求乱码
		request.setCharacterEncoding("UTF-8");
		
		// 获取请求的 鉴别 字符串。----- 鉴别到底是什么功能进来了
		String action = request.getParameter("action");

		try {
			// 通过反射 获取到 请求的功能方法
			Method method = getClass().getDeclaredMethod(action,
					HttpServletRequest.class, HttpServletResponse.class);
			// 调用功能方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
