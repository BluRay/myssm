package com.byd.myssm.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.byd.myssm.entity.Book;

public interface BookDao {

	/**
	 * 通过ID查询单本图书
	 * 
	 * @param id
	 * @return
	 */
	Book queryById(long id);

	/**
	 * 查询所有图书
	 * 
	 * @param offset 查询起始位置
	 * @param limit 查询条数
	 * @return
	 */
	List<Book> queryAll(@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset, @Param("limit") int limit);
	
	int getTotalCount();	//获取查询图书总数
	
	boolean updateBook(@Param("book") Book book);	//修改图书信息

	/**
	 * 减少馆藏数量
	 * 
	 * @param bookId
	 * @return 如果影响行数等于>1，表示更新的记录行数
	 */
	int reduceNumber(long bookId);

}
