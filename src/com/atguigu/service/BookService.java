package com.atguigu.service;

import java.util.List;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

public interface BookService {

	public void addBook(Book book);
	
	public void deleteBookById(int id);
	
	public void updateBook(Book book);

	public List<Book> queryBooks();
	
	public Book queryBookById(int id);

	public Page<Book> page(int pageNo, int pageSize);

	public Page<Book> pageByPrice(int pageNo, int pageSize, double min,
			double max);
}
