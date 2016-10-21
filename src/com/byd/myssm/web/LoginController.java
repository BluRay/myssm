package com.byd.myssm.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("---->loginController login");
		if (request.getSession().getAttribute("user") != null) {
			logger.info("---->LoginInterceptor 已登陆 user = " + request.getSession().getAttribute("user"));
			//return "index";
			return "redirect:/jjq";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	private String logout(HttpServletRequest request){
		logger.info("---->loginController logout");
		HttpSession session= request.getSession();
		session.setAttribute("user", null);
		session.setAttribute("info", null);
		return "login";
	}
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	private String dologin(HttpServletRequest request){
		HttpSession session= request.getSession();
		String username = request.getParameter("username");
		//TODO登录验证
		if("admin".equals(username)){
			session.setAttribute("user", username);
			//return "redirect:/index";
			return "redirect:/jjq";
		}else{
			session.setAttribute("user", null);
			session.setAttribute("info", username + "登陆失败！");
			return "redirect:/login";
		}
	}
}
