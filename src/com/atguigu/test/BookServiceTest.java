package com.atguigu.test;

import org.junit.Test;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class BookServiceTest {

	@Test
	public void testAddBook() {
		BookService bookService = new BookServiceImpl();
		bookService.addBook(new Book(0, "afsdf", "asdf", 9.9, 9, 9, null));
	}

	@Test
	public void testDeleteBookById() {
		new BookServiceImpl().deleteBookById(22);
	}

	@Test
	public void testUpdateBook() {
		BookService bookService = new BookServiceImpl();
		bookService.updateBook(new Book(22, "2afsdf", "2asdf", 29.9, 29, 29, null));
	}

	@Test
	public void testQueryBooks() {
		BookService bookService = new BookServiceImpl();
		System.out.println( bookService.queryBooks().size() );
	}

	@Test
	public void testQueryBookById() {
		System.out.println( new BookServiceImpl().queryBookById(22) );
	}
	
	@Test
	public void testPage() throws Exception {
		System.out.println( new BookServiceImpl().page(2, Page.PAGE_SIZE) );
	}
	
	@Test
	public void testPageByPrice() throws Exception {
		System.out.println( new BookServiceImpl().pageByPrice(1, Page.PAGE_SIZE,10,50) );
	}

}
