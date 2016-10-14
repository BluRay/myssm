package com.byd.myssm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(HttpServletRequest request){
		logger.info("---->loginController login");
		HttpSession session= request.getSession();
		session.setAttribute("user", null);
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logout(HttpServletRequest request){
		logger.info("---->loginController logout");
		HttpSession session= request.getSession();
		session.setAttribute("user", null);
		return "login";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	private String dologin(HttpServletRequest request){
		HttpSession session= request.getSession();
		String username = request.getParameter("username");
		logger.info("---->loginController dologin username = " + username);
		//TODO登录验证
		if("admin".equals(username)){
			session.setAttribute("user", username);
		}else{
			session.setAttribute("user", null);
		}
		
		return "index";
	}
}
