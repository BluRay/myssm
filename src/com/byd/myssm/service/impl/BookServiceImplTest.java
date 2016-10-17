package com.byd.myssm.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.byd.myssm.entity.Book;
import com.byd.myssm.service.BookService;

public class BookServiceImplTest {
	@Autowired
    private BookService bookService;
	
	@Test
    public void testAppoint() throws Exception{
		List<Book> booklist = bookService.getList("name",0,15);
		System.out.println("test");
	}

}
