package com.terrase.frame.util;

import java.util.Map;

public class QueryUtil {
	public static Map<String, Object> noValue(Map<String, Object> predicates, String condition) {
		predicates.put(condition + " and 1 = ? ", 1);
		return predicates;
	}

	public static Map<String, Object> multiValue(Map<String, Object> predicates, String condition, Object... objects) {
		boolean first = true;

		for (Object object : objects) {
			if (first) {
				predicates.put(condition, object);
				first = false;
			} else {
				boolean duplicate = true;
				int i = 0;
				while (duplicate) {
					i++;
					String key = i + "=" + i;

					if (predicates.get(key) == null) {
						predicates.put(key, object);
						duplicate = false;
					}
				}
			}
		}

		return predicates;
	}
}
