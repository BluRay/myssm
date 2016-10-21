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
            //String sql = "UPDATE jjq_jjq a,car_info b SET a.jjq_coid = b.company WHERE a.jjq_cepai = b.cepai";
            //String sql = "update jjq_jjq j set j.jjq_coid = (select c.company from car_info c where c.cepai = j.jjq_cepai) ";
            String sql = "update jjq_jjq set jjq_jjq.jjq_coid='eeess' where jjq_jjq.jjq_cepai='000'";
//            		update daolu set daolu.ID=daolu_info.id 
//            		from daolu Left join daolu_info on daolu.NAME = daolu_info.name

            stat.executeUpdate(sql);
           
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
