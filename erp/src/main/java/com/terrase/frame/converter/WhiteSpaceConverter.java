package com.terrase.frame.converter;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class WhiteSpaceConverter implements Converter {
	private static final char NO_BREAK_SPACE = '\u00A0';

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (component instanceof EditableValueHolder) {
			throw new IllegalArgumentException("Cannot use SpacePreserver converter on editable controls.");
		}
		return value == null ? null : value.toString().replace(' ', NO_BREAK_SPACE);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		throw new UnsupportedOperationException("Output converter only");
	}
}