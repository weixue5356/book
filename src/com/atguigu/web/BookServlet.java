package com.atguigu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.Utils;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();

	/**
	 * 图书列表查询功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用bookService.queryBooks查询全部图书
		List<Book> books = bookService.queryBooks();
		// 把查询到全部图书信息，保存到request域中
		request.setAttribute("books", books);
		// 转发到/pages/manager/book_manager.jsp页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);
	}
	
	protected void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求的参数
		int pageNo = Utils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = Utils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		// 调用BookService.page(pageNo,pageSize):Page分页对象
		Page<Book> page = bookService.page(pageNo,pageSize);
		page.setUrl("manager/bookServlet?action=page");
		// 保存分页对象到request域中
		request.setAttribute("page", page);
		// 转发到pages/manager/book_manager.jsp
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	/**
	 * 添加图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的参数。封装成为book对象
		Book book = Utils
				.copyParamToBean(new Book(), request.getParameterMap());
		// 调用bookService.addBook(book);添加图书
		bookService.addBook(book);
		// 跳转回图书列表页面。
		// request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,
		// response);
		// list(request, response);
		response.sendRedirect(request.getContextPath()
				+ "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}

	/**
	 * 删除图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1 获取请求的参数id
		int id = Utils.parseInt(request.getParameter("id"), 0);
		// 2 调用bookService.deleteBookById(id);
		bookService.deleteBookById(id);
		// 3 重定向到图书列表管理页面
		response.sendRedirect(request.getContextPath()
				+ "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
	}

	/**
	 * 更新图书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Book book = Utils
				.copyParamToBean(new Book(), request.getParameterMap());
		bookService.updateBook(book);
		// 3 重定向到图书列表管理页面
		response.sendRedirect(request.getContextPath()
				+ "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}

	/**
	 * 获取需要修改的图书信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Utils.parseInt(request.getParameter("id"), 0);
		Book book = bookService.queryBookById(id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(
				request, response);
	}

}
