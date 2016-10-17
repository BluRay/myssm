package com.byd.myssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byd.myssm.dao.BookDao;
import com.byd.myssm.dto.AppointExecution;
import com.byd.myssm.entity.Book;
import com.byd.myssm.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private BookDao bookDao;
	
	@Override
	public Book getById(long bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Transactional 标注在 Class 上面， 那么将会对这个 Class 里面所有的 public 方法都包装事务方法. 
	 * 它有几个属性是可以配置的  readOnly, isolation, propagation,rollbackFor, noRollbackFor 。
	 * 如果标记 readOnly=true, 那么就只能选择了，因为只有查询语句才能执行，
	 * 如果是insert,update,delete 等，应该是readOnly=false, 
	 * 不过默认是false的。
	 * rollbackFor 和 noRollbackFor 也是比较重要的两个属性. 默认情况下在有异常 RuntimeException  
	 * 抛出或者 unchecked 异常抛出时，会回滚.
	 * 1 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class) 
	 * 2 让unchecked例外不回滚： @Transactional(notRollbackFor=RunTimeException.class) 
	 */

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<Book> getList(String asc,int offset,int limit) {		
		logger.info("---->BookServiceImpl getList");
		List<Book> list = new ArrayList<Book>();
		list = bookDao.queryAll(offset, limit);
		//throw new RuntimeException("A runtime exception");	//role back
		return list;
	}

	@Override
	public int getTotalCount() {
		return bookDao.getTotalCount();
	}

	@Override
	public AppointExecution appoint(long bookId, long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
