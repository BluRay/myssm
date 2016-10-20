package com.byd.myssm.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.byd.myssm.dto.Result;
import com.byd.myssm.entity.Car;
import com.byd.myssm.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarService carService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		logger.info("---->CarController list");
		return "car/list";
	}
	
	@RequestMapping(value = "/getCarList", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String getCarList(@Param("search") String search,@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset,@Param("limit") int limit) throws IOException{				
		logger.info("---->CarController::getCarList sort = " + sort + ";order = " + order + ";offset = " + offset + ";limit = " + limit);
		List<Car> list = new ArrayList<Car>();
		list = carService.getList(search,sort,order,offset,limit);
		int total = carService.getTotalCount(search);
		logger.info("---->total = " + total);
		return new Result<List<Car>>(true,list,total).toJsonString();
	}
	
	@RequestMapping(value = "/updateCar", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String updateCar(@Param("param") String param, @Param("id") String id, @Param("value") String value) throws IOException{
		value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		carService.updateCar(param, id, value);		
		return "";
	}

}