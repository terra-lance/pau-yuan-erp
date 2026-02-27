package com.terrase.spring.util;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextFactory implements ServletContextAware {

	private static ServletContext servletContext;
	private static ApplicationContext applicationContext;
	private static String applicationName;

	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		}

		return applicationContext;
	}

	public static String getApplicationName() {
		if (applicationName == null) {
			applicationName = getApplicationContext().getApplicationName();
			if (StringUtils.startsWith(applicationName, "/")) {
				applicationName = StringUtils.substring(applicationName, 1);
			}
		}
		return applicationName;
	}

	@SuppressWarnings("static-access")
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}