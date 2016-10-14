package com.byd.myssm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<String> excludedUrls;

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludedUrls = excludeUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获得请求路径的uri
		String uri = request.getRequestURI();
		logger.info("---->LoginInterceptor requestUri = " + uri);
		for (String url : excludedUrls) {
			if (uri.endsWith(url)) {
				//response.sendRedirect("/myssm/login/login/");
				return true;
			}
		}

		// 进入登录页面，判断session中是否有key，有的话重定向到首页，否则进入登录界面
		if (uri.contains("login/login")) {
			if (request.getSession().getAttribute("user") != null) {
				logger.info("---->LoginInterceptor 已登陆 user = " + request.getSession().getAttribute("user"));
				response.sendRedirect(request.getContextPath() + "/index");// 默认跟路径为首页
			} else {
				logger.info("---->LoginInterceptor 未登陆");
				return true;// 继续登陆请求
			}
		}
		if (uri.contains("login/dologin")) {
			return true;
		}

		// 其他情况判断session中是否有key，有的话继续用户的操作
		if (request.getSession().getAttribute("user") != null) {
			return true;
		}

		// 最后的情况就是进入登录页面
		response.sendRedirect("/myssm/login/login");
		return false;
	}
}
