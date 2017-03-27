package com.byd.myssm.web;

import java.io.IOException;
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
import com.byd.myssm.entity.Company;
import com.byd.myssm.entity.Modinfo;
import com.byd.myssm.service.CarService;

@Controller
@RequestMapping("/car")
public class CarController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarService carService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		return "car/list";
	}
	
	@RequestMapping(value = "/companyList", method = RequestMethod.GET)
	private String companyList(Model model) {
		return "car/companyList";
	}
	
	@RequestMapping(value = "/modList", method = RequestMethod.GET)
	private String modList(Model model) {
		return "car/modList";
	}
	
	@RequestMapping(value = "/getCarList", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String getCarList(@Param("search") String search,@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset,@Param("limit") int limit) throws IOException{				
		if (search!= null) search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
		logger.info("---->CarController::getCarList sort = " + sort + ";order = " + order + ";offset = " + offset + ";limit = " + limit);
		List<Car> list = new ArrayList<Car>();
		list = carService.getList(search,sort,order,offset,limit);
		int total = carService.getTotalCount(search);
		return new Result<List<Car>>(true,list,total).toJsonString();
	}
	
	@RequestMapping(value = "/updateCar", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String updateCar(@Param("param") String param, @Param("id") String id, @Param("value") String value) throws IOException{
		//logger.info("---->value = " + value);
		//value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		carService.updateCar(param, id, value);	
		return "{\"success\":true}";
	}
	
	@RequestMapping(value = "/deleteCar", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String deleteCar(@Param("id") String id){
		carService.deleteCar(id);
		return "{\"success\":true}";
	}
	
	@RequestMapping(value = "/getCompanyList", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String getCompanyList(@Param("search") String search,@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset,@Param("limit") int limit) throws IOException{				
		if (search!= null) search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
		logger.info("---->CarController::getCompanyList sort = " + sort + ";order = " + order + ";offset = " + offset + ";limit = " + limit);
		List<Company> list = new ArrayList<Company>();
		list = carService.getCompanyList(search,sort,order,offset,limit);
		int total = carService.getCompanyTotalCount(search);
		return new Result<List<Company>>(true,list,total).toJsonString();
	}
	
	@RequestMapping(value = "/updateCompany", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String updateCompany(@Param("param") String param, @Param("id") String id, @Param("value") String value) throws IOException{
		value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
		carService.updateCompany(param, id, value);	
		return "{\"success\":true}";
	}
	
	@RequestMapping(value = "/deleteCompany", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String deleteCompany(@Param("id") String id){
		carService.deleteCompany(id);
		return "{\"success\":true}";
	}
	
	@RequestMapping(value = "/getModList", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String getModList(@Param("search") String search,@Param("moder") String moder,@Param("date") String date,@Param("order") String order) throws IOException{				
		List<Modinfo> list = new ArrayList<Modinfo>();
		
		return new Result<List<Modinfo>>(true,list).toJsonString();
		//return "{\"success\":true}";
	}

}
