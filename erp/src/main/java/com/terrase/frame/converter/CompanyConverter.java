package com.terrase.frame.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;

import com.terrase.frame.data.Company;
import com.terrase.frame.service.CompanyService;
import com.terrase.spring.util.SpringUtil;

public class CompanyConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (!StringUtils.isNumeric(arg2)) {
			return null;
		} else {
			CompanyService classSvc = (CompanyService) SpringUtil.lookup(CompanyService.class.getName());
			return classSvc.find(Long.parseLong(arg2));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";
		if (arg2 instanceof Company) {
			str = "" + ((Company) arg2).getId();
		}
		return str;
	}
}
