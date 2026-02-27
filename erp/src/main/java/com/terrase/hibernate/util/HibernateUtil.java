package com.terrase.hibernate.util;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"../ctx-spring.xml");
			sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		} catch (Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
		return sessionFactory;
	}
}