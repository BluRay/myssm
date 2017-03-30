package com.byd.myssm.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private List<String> excludedUrls;

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludedUrls = excludeUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获得请求路径的uri
		String uri = request.getRequestURI();
		for (String url : excludedUrls) {
			if (uri.endsWith(url)) {
				//不拦截静态资源文件
				return true;
			}
		}

		// 进入登录页面，在LoginController中判断是否已登录，有的话重定向到首页，否则进入登录界面
		if (uri.contains("login/login")) {
			return true;
		}
		
		if (uri.contains("login/dologin")) {
			return true;
		}

		// 其他情况判断session中是否有key，有的话继续用户的操作
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}

		String url = request.getRequestURI();		//获取上个页面的url  
		HttpSession session= request.getSession();
		session.setAttribute("redirectUrl", url);	//把url放到session

		// 最后的情况就是进入登录页面
		response.sendRedirect("/myssm/login/login");
		return false;
	}
}
