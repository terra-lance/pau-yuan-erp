package com.terrase.spring.util;

import java.io.IOException;

import org.springframework.core.io.Resource;

public final class SpringUtil {

	@SuppressWarnings("unchecked")
	public static <T> T lookup(String id) {
		return (T) ApplicationContextFactory.getApplicationContext().getBean(id);
	}

	public static <T> T lookup(Class<T> clazz) {
		return lookup(clazz.getName());
	}

	public static Resource[] findFiles(String path) {
		Resource[] res;
		try {
			res = ApplicationContextFactory.getApplicationContext().getResources(path);
			return res;
		} catch (IOException e) {

		}
		return null;
	}
}
