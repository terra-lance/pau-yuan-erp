package com.terrase.frame.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;

public class TriStateCheckboxBooleanConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (!StringUtils.isNumeric(arg2)) {
			return null;
		} else {
			switch (arg2) {
			case "0":
				return null;
			case "1":
				return true;
			case "2":
				return false;
			default:
				return null;
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return null;
	}
}
