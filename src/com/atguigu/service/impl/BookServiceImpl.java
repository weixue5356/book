package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

public class BookServiceImpl implements BookService{

	private BookDao bookDao = new BookDaoImpl(); 
	
	@Override
	public void addBook(Book book) {
		bookDao.saveBook(book);
	}

	@Override
	public void deleteBookById(int id) {
		bookDao.deleteBookById(id);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public List<Book> queryBooks() {
		return bookDao.queryBooks();
	}

	@Override
	public Book queryBookById(int id) {
		return bookDao.queryBookById(id);
	}

	@Override
	public Page<Book> page(int pageNo, int pageSize) {
		Page<Book> page = new Page<Book>();
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总记录数
		int pageTotalCount = bookDao.queryForPageTotalCount();
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 求总页码
		int pageTotal = page.getPageTotalCount()/page.getPageSize();
		// 判断是否除尽。如果除不尽。说明还有剩下的记录，需要单独占一页
		if( page.getPageTotalCount() % page.getPageSize() > 0 ) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		// 设置当前页码
		page.setPageNo(pageNo);
		// 由公式（pageNo-1）* pageSize得到begin的值。
		int begin = (page.getPageNo()-1)*page.getPageSize();
		// 求当前页数据
		List<Book> items = bookDao.queryForPageItems(begin,page.getPageSize());
//		设置当前页数据
		page.setItems(items);
		
		return page;
	}

	@Override
	public Page<Book> pageByPrice(int pageNo, int pageSize, double min,
			double max) {
		Page<Book> page = new Page<Book>();
		// 设置每页显示的数量
		page.setPageSize(pageSize);
		// 求总记录数
		int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
		// 设置总记录数
		page.setPageTotalCount(pageTotalCount);
		// 求总页码
		int pageTotal = page.getPageTotalCount()/page.getPageSize();
		// 判断是否除尽。如果除不尽。说明还有剩下的记录，需要单独占一页
		if( page.getPageTotalCount() % page.getPageSize() > 0 ) {
			pageTotal++;
		}
		// 设置总页码
		page.setPageTotal(pageTotal);
		// 设置当前页码
		page.setPageNo(pageNo);
		// 由公式（pageNo-1）* pageSize得到begin的值。
		int begin = (page.getPageNo()-1)*page.getPageSize();
		// 求当前页数据
		List<Book> items = bookDao.queryForPageItemsByPrice(begin,page.getPageSize(),min,max);
//		设置当前页数据
		page.setItems(items);
		
		return page;
	}

}
