package com.terrase.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	@SuppressWarnings("unchecked")
	public static <T> List<T> arrayToList(Object array) {
		int length = Array.getLength(array);
		List<T> values = new ArrayList<>(length);

		for (int i = 0; i < length; i++) {
			values.add((T) Array.get(array, i));
		}

		return values;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> arrayToList(Object[] array) {
		List<T> values = new ArrayList<>(array.length);

		for (Object o : array) {
			values.add((T) o);
		}

		return values;
	}
}
