package com.terrase.frame.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;

import com.terrase.frame.data.Profile;
import com.terrase.frame.service.ProfileService;
import com.terrase.spring.util.SpringUtil;

public class ProfileConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (!StringUtils.isNumeric(arg2)) {
			return null;
		} else {
			ProfileService classSvc = (ProfileService) SpringUtil.lookup(ProfileService.class.getName());
			return classSvc.find(Long.parseLong(arg2));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";
		if (arg2 instanceof Profile) {
			str = "" + ((Profile) arg2).getId();
		}
		return str;
	}
}
