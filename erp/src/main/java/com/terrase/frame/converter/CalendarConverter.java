package com.terrase.frame.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.component.calendar.Calendar;

import com.terrase.util.StringUtil;

public class CalendarConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
		try {
			return LocalDate.parse(value, formatter);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || (value instanceof String && StringUtil.isBlank((String) value))) {
			return "";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(extractPattern(component, context));
		return formatter.format((LocalDate) value);
	}

	private String extractPattern(UIComponent component, FacesContext context) {
		if (component instanceof Calendar) {
			Calendar calendarComponent = (Calendar) component;
			return calendarComponent.getPattern();
		}

		return null;
	}
}
