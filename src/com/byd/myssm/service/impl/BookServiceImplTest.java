package com.byd.myssm.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
		//List<Book> booklist = bookService.getList("desc","name",0,15);
		System.out.println("test");
		try {
            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
            String url = "jdbc:Access:////home/yangke/JJQMS.mdb";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            Statement stat =conn.createStatement();
            String sql = "select * from jjq_company where co_ID = '7'";
            //String sql = "update jjq_company set co_name = '123' where co_ID = '7'";
            
            
            ResultSet rs =stat.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString(2));
            }
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
