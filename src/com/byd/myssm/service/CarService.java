package com.byd.myssm.service;

import java.util.List;
import com.byd.myssm.entity.Car;
import com.byd.myssm.entity.Company;
import com.byd.myssm.entity.Modinfo;
import com.byd.myssm.entity.Worker;

public interface CarService {
	
	/**
	 * 分页查询车辆信息
	 * @param sort
	 * @param asc
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Car> getList(String search,String sort,String asc,int offset,int limit);
	
	int getTotalCount(String search);

	boolean updateCar(String param,String id,String value);
	
	boolean deleteCar(String id);
	
	List<Company> getCompanyList(String search,String sort,String asc,int offset,int limit);
	
	int getCompanyTotalCount(String search);
	
	boolean updateCompany(String param,String id,String value);
	
	boolean deleteCompany(String id);
	
	boolean dologin(String username,String password);
	
	List<Modinfo> getModList(String search,String moder,String date);
	
	public List<Worker> getWorkerList(String search,String sort, String asc, int offset, int limit);
	public int getWorkerTotal(String search);
}
