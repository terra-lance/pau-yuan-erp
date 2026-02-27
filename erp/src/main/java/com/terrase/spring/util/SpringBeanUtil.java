package com.terrase.spring.util;

import java.io.IOException;

import org.springframework.core.io.Resource;

public class SpringBeanUtil {
	
	public static Object lookup(String id) {
		Object obj = ApplicationContextFactory.getApplicationContext().getBean(
				id);
		return obj;
	}

	public static Resource[] findFiles(String path) {
		Resource[] res;
		try {
			res = ApplicationContextFactory.getApplicationContext()
					.getResources(path);
			return res;
		} catch (IOException e) {

		}
		return null;
	}

}
