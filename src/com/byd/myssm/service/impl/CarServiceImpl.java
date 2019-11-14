package com.byd.myssm.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.byd.myssm.dao.CarDao;
import com.byd.myssm.entity.Car;
import com.byd.myssm.entity.Company;
import com.byd.myssm.entity.Modinfo;
import com.byd.myssm.entity.Worker;
import com.byd.myssm.service.CarService;
@Service
public class CarServiceImpl implements CarService {
	@Autowired
    private CarDao carDao;

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
	        	offset + " jjq_ID from jjq_jjq where (jjq_cepai like '%"+search+"%' OR jjq_no like '%"+search+"%' OR jjq_coid like '%"+search+"%') order by jjq_ID) and (jjq_cepai like '%"+search+"%' OR jjq_no like '%"+search+"%' OR jjq_coid like '%"+search+"%') order by jjq_ID";
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
	        	sql = "select count(*) as count from jjq_jjq where (jjq_cepai like '%"+search+"%') OR (jjq_no like '%"+search+"%')";
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

	@Override
	public List<Company> getCompanyList(String search, String sort, String asc, int offset, int limit) {
		List<Company> list = new ArrayList<Company>();  
		try {
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
			//prop.load(in);   
            String url = prop.getProperty("access_path").trim();
            in.close();
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        Statement stat =conn.createStatement();
	        String sql = "";
	        if (search != null){
	        	sql = " SELECT TOP " + limit + "  *  FROM jjq_company where co_ID not in(select top " +
	        	offset + " co_ID from jjq_company where ( co_name like '%"+search+"%') order by co_ID) and  co_name like '%"+search+"%' order by co_ID";
	        }else{
	        	sql = " SELECT TOP " + limit + "  *  FROM jjq_company where co_ID not in(select top " +
	    	        	offset + " co_ID from jjq_company order by co_ID) order by co_ID";
	        }
	        ResultSet rs =stat.executeQuery(sql);
	        while(rs.next()) {
	        	Company company = new Company();
	        	company.setId(rs.getInt("co_ID"));
	        	company.setName(rs.getString("co_name"));
	        	company.setAddress(rs.getString("co_add"));
	        	company.setCeo(rs.getString("co_ceo"));
	        	company.setTel(rs.getString("co_tel"));
	        	company.setCode(rs.getString("co_code"));
	        	company.setMemo(rs.getString("co_memo"));
	        	list.add(company);
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
	public int getCompanyTotalCount(String search) {
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
	        	sql = "select count(*) as count from jjq_company where (co_name like '%"+search+"%')";
	        }else{
	        	sql = "select count(*) as count from jjq_company";
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
	public boolean updateCompany(String param, String id, String value) {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
            String url = prop.getProperty("access_path").trim();
            in.close();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        
	        Statement stat =conn.createStatement();
	        String sql = "update jjq_company set " + param + " = '" + value + "' where co_ID = '" + id + "'";
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
	public boolean deleteCompany(String id) {
		try {
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
            String url = prop.getProperty("access_path").trim();
            in.close();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        
	        Statement stat =conn.createStatement();
	        String sql = "delete from jjq_company where co_ID = '" + id + "'";
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

	@Override
	public boolean dologin(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Modinfo> getModList(String search, String moder, String date) {
		List<Modinfo> list = new ArrayList<Modinfo>();
		
		try{
			Properties prop = new Properties();
			InputStream in = CarServiceImpl.class.getResourceAsStream("/resources/jdbc.properties");
			prop.load(new InputStreamReader(in, "UTF-8")); 
			//prop.load(in);   
	        String url = prop.getProperty("access_path").trim();
	        in.close();
			Class.forName("com.hxtt.sql.access.AccessDriver").newInstance();
	        Connection conn = DriverManager.getConnection(url, "", "");
	        Statement stat =conn.createStatement();
	        if (search==null)search=date;
	        String sql = "SELECT top 100 * FROM jjq_mod where mod_date like '%"+search+"%' AND mod_moder like '%"+moder+"%'";
	        System.out.println("---->getModList sql = " + sql);
	        ResultSet rs =stat.executeQuery(sql);

	        while(rs.next()) {
	        	Modinfo modinfo = new Modinfo();	        	
	        	modinfo.setId(rs.getInt("mod_ID"));
	        	modinfo.setModer_name(rs.getString("mod_moder"));
	        	modinfo.setJjq_no(rs.getString("mod_bianhao"));
	        	modinfo.setModer_date(rs.getString("mod_date"));
	        	modinfo.setMod_info(rs.getString("mod_mingxi"));
	        	modinfo.setPrice(rs.getString("mod_money"));
	        	modinfo.setS_price(rs.getString("mod_smoney"));
	        	list.add(modinfo);
	        }
	        
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	@Override
	public List<Worker> getWorkerList(String search,String sort, String asc, int offset, int limit){
		List<Worker> list = new ArrayList<Worker>();
		list = carDao.getWorkerList(sort, sort, offset, limit);
		return list;
	}
	
}

