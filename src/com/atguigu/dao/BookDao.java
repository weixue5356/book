package com.atguigu.dao;

import java.util.List;

import com.atguigu.pojo.Book;

public interface BookDao {

	public int saveBook(Book book);

	public int deleteBookById(int id);

	public int updateBook(Book book);

	public List<Book> queryBooks();

	public Book queryBookById(int id);

	public int queryForPageTotalCount();

	public List<Book> queryForPageItems(int begin, int pageSize);

	public int queryForPageTotalCountByPrice(double min, double max);

	public List<Book> queryForPageItemsByPrice(int begin, int pageSize,
			double min, double max);
}
