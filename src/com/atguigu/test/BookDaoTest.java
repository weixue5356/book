package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

public class BookDaoTest {

	@Test
	public void testSaveBook() {
		System.out.println(new BookDaoImpl().saveBook(new Book(0, "如何xxx",
				"asdf", 99, 99, 99, null)));
	}

	@Test
	public void testDeleteBookById() {
		System.out.println(new BookDaoImpl().deleteBookById(21));
	}

	@Test
	public void testUpdateBook() {
		System.out.println(new BookDaoImpl().updateBook(new Book(21, "2如何xxx",
				"2asdf", 299, 299, 299, null)));
	}

	@Test
	public void testQueryBooks() {
		System.out.println(new BookDaoImpl().queryBooks().size());
	}

	@Test
	public void testQueryBookById() {
		System.out.println(new BookDaoImpl().queryBookById(21));
	}
	
	@Test
	public void testQueryForPageTotalCount() throws Exception {
		BookDao bookDao = new BookDaoImpl();
		System.out.println( bookDao.queryForPageTotalCount() );
	}
	@Test
	public void testQueryForPageTotalCountByPrice() throws Exception {
		BookDao bookDao = new BookDaoImpl();
		System.out.println( bookDao.queryForPageTotalCountByPrice(10, 50) );
	}
	
	@Test
	public void testQueryForPageItems() throws Exception {
		BookDao bookDao = new BookDaoImpl();
		System.out.println( bookDao.queryForPageItems(4, Page.PAGE_SIZE) );
	}
	
	@Test
	public void testQueryForPageItemsByPrice() throws Exception {
		BookDao bookDao = new BookDaoImpl();
		System.out.println( bookDao.queryForPageItemsByPrice(4, Page.PAGE_SIZE,10,50) );
	}

}
