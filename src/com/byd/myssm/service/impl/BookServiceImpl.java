package com.byd.myssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.byd.myssm.dto.AppointExecution;
import com.byd.myssm.entity.Book;
import com.byd.myssm.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Book getById(long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getList() {
		// TODO Auto-generated method stub
		
		logger.info("---->BookServiceImpl getList");
		Book book = new Book();
		book.setBookId(1);
		book.setName("TESTBOOK");
		Book book2 = new Book();
		book2.setBookId(2);
		book2.setName("TESTBOOK2");
		//return new Result<Book>(true,book);
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		list.add(book2);
		
		return list;
	}

	@Override
	public AppointExecution appoint(long bookId, long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
