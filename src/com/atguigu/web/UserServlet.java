package com.atguigu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.Utils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	protected void ajaxExistsUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1 获取请求的参数 用户名
		String username = request.getParameter("username");
		boolean isExists = userService.existsUsername(username);
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("isExists", isExists);
		response.getWriter().write( new Gson().toJson(result) );
	}
	
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 从Session中删除用户登录成功的信息或让服务器的Session失效
//		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		// 最后重定向到首页
		response.sendRedirect(request.getContextPath());
		
	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1、获取请求的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = Utils
				.copyParamToBean(new User(), request.getParameterMap());

		// 2、调用UserService.login( );
		User loginUser = userService.login(user);
		// 3、如果登录有值返回。说明登录成功
		if (loginUser != null) {
			// 我们把用户登录成功之后的信息，保存到Session域对象中。
			request.getSession().setAttribute("user", loginUser);
			request.getRequestDispatcher("/pages/user/login_success.jsp")
					.forward(request, response);
		} else {
			// 保存需要回显的错误信息
			request.getSession().setAttribute("msg", "用户名或密码错误！");
			request.getSession().setAttribute("username", user.getUsername());
			
			response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
			
//			request.setAttribute("msg", "用户名或密码错误！");
//			request.setAttribute("username", user.getUsername());

			// 如果登录没有值返回null的情况，说明登录失败
//			request.getRequestDispatcher("/pages/user/login.jsp").forward(
//					request, response);
		}
	}

	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 从Session中获取生成的验证码
		String sessionCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		// 删除Session中的谷歌验证码
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		String code = request.getParameter("code");
		//
		User user = Utils
				.copyParamToBean(new User(), request.getParameterMap());

		// 2、检查验证码是否相同
		if (sessionCode!= null && sessionCode.equalsIgnoreCase(code)) {
			// 3、检查用户名已存可用
			if (userService.existsUsername(user.getUsername())) {

				// 保存需要回显的信息，到request域中
				request.setAttribute("msg", "用户名已存在！");
				request.setAttribute("username", user.getUsername());
				request.setAttribute("email", user.getEmail());

				// 不可用
				// 跳回注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(
						request, response);
			} else {
				// 4、调用UserService.regist(user) 保存用户
				userService.regist(user);
				// 5、跳到注册成功页面
				request.getRequestDispatcher("/pages/user/regist_success.jsp")
						.forward(request, response);
			}
		} else {
			// 保存需要回显的信息，到request域中
			request.setAttribute("msg", "验证码不正确");
			request.setAttribute("username", user);
			request.setAttribute("email", user);

			// 跳回注册页面
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}
	}

}
