package com.atguigu.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.Utils;
import com.google.gson.Gson;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();
	
	protected void updateCount(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的参数
		int id = Utils.parseInt(request.getParameter("id"), 0);
		int count = Utils.parseInt(request.getParameter("count"),1);
		// 从Session中获取Cart购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			//修改商品数量
			cart.updateCount(id, count);
			// 3 重定向回原来购物车页面
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	protected void deleteItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1 接收请求参数
		int id = Utils.parseInt(request.getParameter("id"), 0);
		// 2 从Session中获取Cart购物车对象。调用deleteItem方法，执行删除操作
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteItem(id);
			// 3 重定向回原来购物车页面
			response.sendRedirect(request.getHeader("referer"));
		}

	}

	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 只要从Session中获取之前的购物车对象。调用clear方法清空。
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 清空之后重定向回原来购物车页面。展示清空状态
		if (cart != null) {
			cart.clear();
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	protected void addItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1、获取商品编号id。
		int id = Utils.parseInt(request.getParameter("id"), 0);
		// 2、调用BookService.queryBookById(id)：Book
		Book book = bookService.queryBookById(id);
		// 3、把book图书信息。转换成为CartItem实例
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1,
				book.getPrice(), book.getPrice());
		// 4、调用cart.addItem(cartItem);
		// 应该先从Session中获取Cart购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 看看，手里有没有购物车。如果没有，找一辆
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		System.out.println(cart);
		//把最后一个添加的商品名称，存放到Session中
		request.getSession().setAttribute("last_product_name", cartItem.getName());
		
		
		
		// 5、跳回商品列表页面
		String referer = request.getHeader("referer");
		System.out.println(referer);
		response.sendRedirect(referer);
	}


	protected void ajaxAddItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1、获取商品编号id。
		int id = Utils.parseInt(request.getParameter("id"), 0);
		// 2、调用BookService.queryBookById(id)：Book
		Book book = bookService.queryBookById(id);
		// 3、把book图书信息。转换成为CartItem实例
		CartItem cartItem = new CartItem(book.getId(), book.getName(), 1,
				book.getPrice(), book.getPrice());
		// 4、调用cart.addItem(cartItem);
		// 应该先从Session中获取Cart购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 看看，手里有没有购物车。如果没有，找一辆
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.addItem(cartItem);
		System.out.println(cart);
		//把最后一个添加的商品名称，存放到Session中
		request.getSession().setAttribute("last_product_name", cartItem.getName());
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cartTotalCount", cart.getTotalCount());
		result.put("lastProductName", cartItem.getName());
		
		response.getWriter().write(new Gson().toJson(result));
	}
	
}
