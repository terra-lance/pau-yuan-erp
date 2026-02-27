package com.terrase.frame.util;

import java.util.Set;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

public class NavigationUtil {

	public static String getViewId(FacesContext faces, String outcome) {
		ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) faces.getApplication()
				.getNavigationHandler();

		for (Set<NavigationCase> navigations : navigationHandler.getNavigationCases().values()) {
			for (NavigationCase navigation : navigations) {
				if (StringUtils.equals(navigation.getFromOutcome(), outcome)) {
					return navigation.getToViewId(faces);
				}
			}
		}

		return null;
	}

	public static String getViewId(String outcome) {
		FacesContext faces = FacesContext.getCurrentInstance();
		return getViewId(faces, outcome);
	}

	public static void performNavigation(FacesContext faces, String outcome) {
		ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) faces.getApplication()
				.getNavigationHandler();
		navigationHandler.performNavigation(outcome);
	}

	public static void performNavigation(String outcome) {
		FacesContext faces = FacesContext.getCurrentInstance();
		performNavigation(faces, outcome);
	}

	public static String urlCombine(String... urls) {
		String url = "";
		for (String s : urls) {
			if (StringUtils.isEmpty(s)) {
				continue;
			}

			if (s.startsWith("/")) {
				url += s;
			} else {
				url += "/" + s;
			}
		}

		return url;
	}

}
