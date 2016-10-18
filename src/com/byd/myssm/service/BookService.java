package com.byd.myssm.service;

import java.util.List;

import com.byd.myssm.dto.AppointExecution;
import com.byd.myssm.entity.Book;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface BookService {

	/**
	 * 查询一本图书
	 * 
	 * @param bookId
	 * @return
	 */
	Book getById(long bookId);

	/**
	 * 分页查询图书
	 * 
	 * @return
	 */
	List<Book> getList(String sort,String asc,int offset,int limit);
	
	int getTotalCount();	//获取查询图书总数
	
	boolean updateBook(Book book);

	/**
	 * 预约图书
	 * 
	 * @param bookId
	 * @param studentId
	 * @return
	 */
	AppointExecution appoint(long bookId, long studentId);

}
