package com.byd.myssm.service;

import java.util.List;
import com.byd.myssm.entity.Car;

public interface CarService {
	
	/**
	 * 分页查询车辆信息
	 * @param sort
	 * @param asc
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Car> getList(String sort,String asc,int offset,int limit);
}
