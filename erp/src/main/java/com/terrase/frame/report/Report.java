package com.terrase.frame.report;

import java.util.HashMap;
import java.util.LinkedHashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {
	private HashMap<String, Object> parameters;
	private String templateFileName;
	private String outputFileName;

	public Report() {
		parameters = new LinkedHashMap<String, Object>();
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}

	public void setParameter(String key, Object value) {
		parameters.put(key, value);
	}
}
