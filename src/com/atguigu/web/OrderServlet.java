package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderServiceImpl();
	
	protected void createOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//需要获取购物车对象。和用户id信息
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart.getItems().isEmpty()) {
			response.sendRedirect(request.getHeader("referer"));
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath()+"/pages/user/login.jsp");
			return;
		}
		int userId = user.getId();
		// 调用OrdeService.createOrder()去实现业务
		String orderId = null;
			orderId = orderService.createOrder(cart, userId);
		// 把生成的订单号，保存到request域中
		request.setAttribute("orderId", orderId);
		// 转发到pages/cart/checkout.jsp页面
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
	}

}
