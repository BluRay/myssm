package com.byd.myssm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

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
		System.out.println("---->test");
		Properties prop = new Properties();
		//InputStream in = Object.class.getResourceAsStream("/resources/jdbc.properties"); 
		InputStream in = Object.class.getResourceAsStream("/resources/jdbc.properties");
		try {   
            prop.load(in);   
            String param1 = prop.getProperty("access_path").trim();
            System.out.println("---->param1 = " + param1);
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
		
		/*try {
            Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
            String url = "jdbc:Access:////home/yangke/JJQMS.mdb";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            Statement stat =conn.createStatement();
            String sql = "update jjq_jjq set jjq_jjq.jjq_coid='eeess' where jjq_jjq.jjq_cepai='000'";

            stat.executeUpdate(sql);
           
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
	}

}
