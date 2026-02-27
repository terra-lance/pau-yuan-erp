package com.terrase.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.terrase.frame.util.NavigationUtil;
import com.terrase.spring.util.ApplicationContextFactory;

public class LoginFilter implements Filter {

	private static String applicationName;

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		applicationName = ApplicationContextFactory.getApplicationName();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
	

		String uri = httpRequest.getRequestURI();
		if (uri.startsWith(NavigationUtil.urlCombine(applicationName, "/api"))) {
			chain.doFilter(request, response);
			return;
		}

		chain.doFilter(request, response);
	}
}
