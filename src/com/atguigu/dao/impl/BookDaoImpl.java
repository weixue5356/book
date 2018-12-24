package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public int saveBook(Book book) {
		String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?)";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(),
				book.getSales(), book.getStock(), book.getImgPath());
	}

	@Override
	public int deleteBookById(int id) {
		String sql = "delete from t_book where id = ?";
		return update(sql, id);
	}

	@Override
	public int updateBook(Book book) {
		System.out.println("BookDaoImpl 中 线程【"
				+ Thread.currentThread().getName() + "】");
		
		String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id = ?";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(),
				book.getSales(), book.getStock(), book.getImgPath(),
				book.getId());
	}

	@Override
	public List<Book> queryBooks() {
		String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
		return queryForList(sql);
	}

	@Override
	public Book queryBookById(int id) {
		String sql = "select `id`, `name` , `author` , `price` , `sales` , "
				+ "`stock` , `img_path` imgPath from t_book where id = ?";
		return queryForOne(sql, id);
	}

	@Override
	public int queryForPageTotalCount() {
		String sql = "select count(*) from t_book";
		Long result = (Long) queryForSingleValue(sql);
		return result.intValue();
	}

	@Override
	public List<Book> queryForPageItems(int begin, int pageSize) {
		String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
		return queryForList(sql, begin, pageSize);
	}

	@Override
	public int queryForPageTotalCountByPrice(double min, double max) {
		String sql = "select count(*) from t_book where price between ? and ?";
		Long result = (Long) queryForSingleValue(sql, min, max);
		return result.intValue();
	}

	@Override
	public List<Book> queryForPageItemsByPrice(int begin, int pageSize,
			double min, double max) {
		String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath "
				+ "from t_book where price between ? and ? order by price limit ?,?";
		return queryForList(sql, min, max, begin, pageSize);
	}

}
