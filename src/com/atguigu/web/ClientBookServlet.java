package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.Utils;

/**
 * Servlet implementation class ClientBookServlet
 */
public class ClientBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

//	protected void page(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 获取请求的参数
//
//		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
//		int pageSize = Utils.parseInt(request.getParameter("pageSize"),
//				Page.PAGE_SIZE);
//		// 调用BookService.page(pageNo,pageSize):Page分页对象
//		Page<Book> page = bookService.page(pageNo, pageSize);
//		page.setUrl("client/bookServlet?action=page");
//		// 保存分页对象到request域中
//		request.setAttribute("page", page);
//		// 转发到pages/manager/book_manager.jsp
//		request.getRequestDispatcher("/pages/client/index.jsp").forward(
//				request, response);
//	}

	protected void pageByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取请求的参数
		double min = Utils.parseDouble(request.getParameter("min"), 0.0);
		double max = Utils.parseDouble(request.getParameter("max"),
				Double.MAX_VALUE);
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = Utils.parseInt(request.getParameter("pageSize"),
				Page.PAGE_SIZE);
		// 调用BookService.pageByPrice():Page对象;
		Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
		
		StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
		if (request.getParameter("min") != null) {
			sb.append("&min=" + request.getParameter("min"));
		} 
		if (request.getParameter("max") != null) {
			sb.append("&max=" + request.getParameter("max"));
		}
		page.setUrl(sb.toString());
		// 把page对象保存到request域中
		request.setAttribute("page", page);
		// 转发到首页中去展示 数据
		request.getRequestDispatcher("/pages/client/index.jsp").forward(
				request, response);
	}

}
