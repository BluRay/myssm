package com.byd.myssm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UtilController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index(HttpServletRequest request, HttpServletResponse response){
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(HttpServletRequest request, HttpServletResponse response){
		//return "index";
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/jjq", method = RequestMethod.GET)
	private String jjq(HttpServletRequest request, HttpServletResponse response){
		return "jjq_index";
	}

}
