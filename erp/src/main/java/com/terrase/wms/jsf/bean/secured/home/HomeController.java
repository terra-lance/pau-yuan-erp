package com.terrase.wms.jsf.bean.secured.home;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.terrase.frame.jsf.bean.system.AuthenticatedBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean(name = "wmsHomeController")
@ViewScoped
public class HomeController extends AuthenticatedBean {
	private static final long serialVersionUID = 1L;

	@Override
	@PostConstruct
	public void init() {
		try {
			super.init();

			title = "Home";
		} catch (Throwable t) {
			addErrorMessage(t);
		}
	}

	public String index() {
		return sessionBean.getSystemNamespace().concat("home");
	}

	public String layout() {
		return "layout";
	}
}
