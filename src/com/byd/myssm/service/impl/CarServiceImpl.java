package com.byd.myssm.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;
import com.byd.myssm.entity.Car;
import com.byd.myssm.service.CarService;
@Service
public class CarServiceImpl implements CarService {

	@Override
	public List<Car> getList(String search,String sort, String asc, int offset, int limit) {
		List<Car> list = new ArrayList<Car>();  
		try {
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
			//prop.load(in);   
            String url = prop.getProperty("access_path").trim();
            in.close();
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
	        //String url = "jdbc:Access:////home/yangke/JJQMS.mdb";
	        Connection conn = DriverManager.getConnection(url, "", "");
	        Statement stat =conn.createStatement();
	        String sql = "";
	        if (search != null){
	        	sql = " SELECT TOP " + limit + "  *  FROM jjq_jjq where jjq_ID not in(select top " +
	        	offset + " jjq_ID from jjq_jjq where (jjq_cepai like '%"+search+"%' OR jjq_no like '%"+search+"%') order by jjq_ID) and (jjq_cepai like '%"+search+"%' OR jjq_no like '%"+search+"%') order by jjq_ID";
	        }else{
	        	sql = " SELECT TOP " + limit + "  *  FROM jjq_jjq where jjq_ID not in(select top " +
	    	        	offset + " jjq_ID from jjq_jjq order by jjq_ID) order by jjq_ID";
	        }
	        	        
	        ResultSet rs =stat.executeQuery(sql);
	        while(rs.next()) {
                //System.out.println(rs.getString(2));
	        	Car car = new Car();
	        	car.setId(rs.getInt("jjq_ID"));
	        	car.setChepai(rs.getString("jjq_cepai"));
	        	car.setGongsi(rs.getString("jjq_coid"));
	        	car.setSiji(rs.getString("jjq_driname"));
	        	car.setDianhua(rs.getString("jjq_dritel"));
	        	car.setAnzhuangriqi(rs.getString("jjq_sdate"));
	        	car.setJijiaqihao(rs.getString("jjq_no"));
	        	car.setBeizhu(rs.getString("tel_memo"));
	        	list.add(car);
            }
	        conn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount(String search) {
		int totalCount = 0;
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
            String url = prop.getProperty("access_path").trim();
            in.close();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        
	        Statement stat =conn.createStatement();
	        String sql = "";
	        if (search != null){
	        	sql = "select count(*) as count from jjq_jjq where (jjq_cepai like '%"+search+"%' OR jjq_no like '%"+search+"%')";
	        }else{
	        	sql = "select count(*) as count from jjq_jjq";
	        }
	        
	        ResultSet rs =stat.executeQuery(sql);
	        rs.next();
	        totalCount = rs.getInt("count");
	        conn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	@Override
	public boolean updateCar(String param, String id, String value) {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
            String url = prop.getProperty("access_path").trim();
            in.close();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        
	        Statement stat =conn.createStatement();
	        String sql = "update jjq_jjq set " + param + " = '" + value + "' where jjq_ID = '" + id + "'";
	        System.out.println(sql);
	        stat.execute(sql);
	        conn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteCar(String id) {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
            String url = prop.getProperty("access_path").trim();
            in.close();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        
	        Statement stat =conn.createStatement();
	        String sql = "delete from jjq_jjq where jjq_ID = '" + id + "'";
	        System.out.println(sql);
	        stat.executeUpdate(sql);
	        conn.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
